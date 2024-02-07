package tech.majaliwa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {

    public static boolean EXIT_GAME = false;
    public static ArrayList<Card> pile = new ArrayList<>();
    public static ArrayList<Card> deck;
    public static boolean playerCanPassAfterPickingOrPlayingCard = true;
    public static boolean playerCanPickCardFromDeck = true;
    public static boolean PICK_FROM_DECK_OR_COUNTER = false;
    public static boolean PICK_ONCE_FROM_DECK = true;
    public static int PICK_COUNT = 0;
    public static boolean AI_TAKES_DAMAGE = false;

    public static void main(String[] args) {
        System.out.println(Rules.WELCOME_MESSAGE);
        System.out.println();

        var scanner = setGameMode();

        deck = Deck.createDeck(Rules.JOKER_MODE);
        Deck.printDeck("Deck of Cards", deck, 4);
        System.out.println("Size of deck: " + deck.size());

        System.out.println("Enter your player name");
        String playerName = scanner.nextLine();

        System.out.println();
        System.out.println("---------- Game in session ----------");
        System.out.println("You are now playing with the A.I");

        System.out.println("Shuffling deck...");
        Collections.shuffle(deck);

        var player = new Player(playerName);
        var AI = new AI("AI");

        System.out.println("Dealing cards...");
        player.setInitialHand(deck);
        AI.setInitialHand(deck);
        System.out.println("Size of deck: " + deck.size());

        gameInSession(player, AI, deck, pile);
        scanner.close();
    }

    public void startGame() {
        main(new String[]{});
    }

    private static Scanner setGameMode() {
        System.out.println("Do you want to play in Joker mode? (y/n). Enter 'e' to exit the game");

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        switch (answer.toLowerCase()) {
            case "y", "yes" -> {
                Rules.JOKER_MODE = true;
                System.out.println(Rules.JOKER);
            }
            case "e", "exit" -> {
                System.out.println("Have a good day!");
                System.exit(0);
            }
            case "n", "no" -> {
                Rules.JOKER_MODE = false;
                System.out.println(Rules.CLASSIC);
            }
            default -> {
                System.out.println("Invalid input. Enter 'e' to exit or 'y' or 'n' to continue");
                return setGameMode();
            }
        }
        return scanner;
    }

    private static void gameInSession(Player player, AI ai, ArrayList<Card> deck, ArrayList<Card> pile) {
        var scanner = new Scanner(System.in);

        do {
            getPile(pile);
            player_s_Turn(player, deck, pile, scanner);
            aI_s_Turn(ai, pile);
        } while (!EXIT_GAME);

        restartGame(deck, pile, scanner);
        scanner.close();
    }

    private static void restartGame(ArrayList<Card> deck, ArrayList<Card> pile, Scanner scanner) {
        System.out.println();
        System.out.println("Do you want to play again? (y/n)");
        var input = scanner.nextLine();
        if (input.equalsIgnoreCase("y")) {
            System.out.println("Restarting game...");
            pile.clear();
            deck.clear();
            EXIT_GAME = false;
            Main.main(new String[]{});
        } else if (input.equalsIgnoreCase("n")) {
            System.out.println("Thanks for playing!");
            System.exit(0);
        } else {
            System.out.println("Invalid input");
            restartGame(deck, pile, scanner);
        }
    }

    private static void player_s_Turn(Player player, ArrayList<Card> deck, ArrayList<Card> pile, Scanner scanner) {
        AI_TAKES_DAMAGE = false;
        do {
            playerChoosesAction(player, playerCanPickCardFromDeck);
            var input = scanner.nextLine();
            var inputIsPickCard = input.equalsIgnoreCase("p");
            var inputIsAcceptDamage = input.equalsIgnoreCase("accept");

            if (inputIsAcceptDamage) {
                var face = pile.get(pile.size() - 1);
                var currentFace = face.face();

                switch (currentFace) {
                    case TWO -> player.pickTwoCards(deck);
                    case THREE -> player.pickThreeCards(deck);
                    case JOKER -> player.pickFiveCards(deck);
                }
                resetDamageRules();

                break;
            }

            if (inputIsPickCard && Game.PICK_FROM_DECK_OR_COUNTER) {
                continue;
            }

            if (inputIsPickCard && !playerCanPickCardFromDeck) {
                System.out.println("Pass your turn or play a card. Try again");
                continue;
            }
            if (inputIsPickCard) {
                pickCard(player, deck);
                continue;
            } else {
                var inputIsPass = input.equalsIgnoreCase("pass");
                if (inputIsPass) {
                    if (playerCanPassAfterPickingOrPlayingCard) {
                        enforcePassRule();
                        System.out.println("You passed your turn");
                        break;
                    } else {
                        System.out.println("You can't pass without picking a card or playing one. Try again");
                        continue;
                    }
                } else {
                    if (cardNotPlayed(player, pile, input)) continue;
                }
            }
            break;
        } while (true);
    }

    private static void resetDamageRules() {
        PICK_FROM_DECK_OR_COUNTER = false;
        playerCanPickCardFromDeck = true;
        playerCanPassAfterPickingOrPlayingCard = true;
    }

    private static void enforcePassRule() {
        // TODO: Remove comments
        playerCanPassAfterPickingOrPlayingCard = false; // player can't pass until a card is picked or played
        playerCanPickCardFromDeck = true; // reset pick count for player
    }

    private static void enforcePickRule() {
        // TODO: Remove comments
        playerCanPickCardFromDeck = false; // player can't pick twice
        playerCanPassAfterPickingOrPlayingCard = true; // player can pass
    }

    private static void pickCard(Player player, ArrayList<Card> deck) {
        var cardToPick = UserInterface.pickCard(deck);
        if (cardToPick == null) {
            reshuffleDeckAndContinuePlaying();
            return;
        }

        player.getHand().add(cardToPick);
        System.out.println("You picked: " + cardToPick);
        enforcePickRule();
    }

    private static boolean cardNotPlayed(Player player, ArrayList<Card> pile, String input) {
        try {
            var numberIsGreaterThanHandSize = Integer.parseInt(input) > player.getHand().size();
            var numberIsLessThanHandSize = Integer.parseInt(input) < 1;
            var enteredNumberIsWrong = numberIsGreaterThanHandSize || numberIsLessThanHandSize;
            if (enteredNumberIsWrong) {
                System.out.println("Wrong card position. Try again");
                return true;
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input or wrong suit. Try again");
            return true;
        }

        return cardIsNotPlayed(player, pile, input);
    }

    static void reshuffleDeckAndContinuePlaying() {
        var getLastCard = Game.pile.get(Game.pile.size() - 1);
        System.out.println("There is nothing left in the deck");
        System.out.println("Shuffling the pile...");
        Collections.shuffle(Game.pile);
        Game.pile.remove(getLastCard);
        Game.deck.addAll(Game.pile);
        Game.pile.clear();
        System.out.println("Pile shuffled and added to the deck");
        Game.pile.add(getLastCard);
        Deck.printDeck("Current pile", Game.pile, 1);
    }

    private static void aI_s_Turn(AI ai, ArrayList<Card> pile) {
        enforcePassRule();
        while (true) {
            System.out.println("AI's turn");
            if (AI_TAKES_DAMAGE) {
                var face = pile.get(pile.size() - 1);
                var currentFace = face.face();

                switch (currentFace) {
                    case TWO -> ai.pickTwoCards(deck);
                    case THREE -> ai.pickThreeCards(deck);
                    case JOKER -> ai.pickFiveCards(deck);
                }
                break;
            }
            var cardPlayed = ai.playCard();
            var isValidCard = isValidCard(cardPlayed, pile);
            if (!isValidCard) {
                getPile(pile);
                continue;
            }
            System.out.println("*".repeat(25));
            System.out.println(ai.getName() + "'s remaining cards " + ai.getHand().size());
            System.out.println("*".repeat(25));
            if (cardPlayed != null) {
                addToPile(cardPlayed, pile);
                var opponentShouldPickFromDeck = ai.checkIfOpponentShouldPickFromDeck(cardPlayed);
                if (opponentShouldPickFromDeck) {
                    enforceDamageRules();
                    break;
                }
            }
            checkIfPlayerWon(ai);
            var canFollowCard = ai.checkIfCanFollowCard();
            if (canFollowCard) {
                continue;
            }
            break;
        }
    }

    private static void enforceDamageRules() {
        PICK_FROM_DECK_OR_COUNTER = true;
        playerCanPickCardFromDeck = false;
        playerCanPassAfterPickingOrPlayingCard = false;
    }

    private static boolean cardIsNotPlayed(Player player, ArrayList<Card> pile, String input) {
        var chosenCard = Integer.parseInt(input);
        var cardPlayed = player.playCard(chosenCard);
        var isValidCard = isValidCard(cardPlayed, pile);
        if (!isValidCard) {
            System.out.println("Invalid card. Try again");
            getPile(pile);
            return true;
        }
        System.out.println(player.getName() + "'s remaining cards " + player.getHand().size());
        addToPile(cardPlayed, pile);
        playerCanPassAfterPickingOrPlayingCard = false;
        checkIfPlayerWon(player);
        var canFollowCard = player.checkIfCanFollowCard();
        if (canFollowCard) {
            System.out.println("You can follow this card with another valid card");
            return true;
        }
        var opponentShouldPickFromDeck = player.checkIfOpponentShouldPickFromDeck(cardPlayed);
        if (opponentShouldPickFromDeck) {
            // a.i picks from deck
            AI_TAKES_DAMAGE = true;
            return false;
        }
        return false;
    }

    static void playerChoosesAction(Player player, boolean playerCanPickCardFromDeck) {
        System.out.println("It's your turn");
        var getHand = player.getHand();
        System.out.print("Your hand: ");
        getHand.forEach(
                card -> System.out.print(card + "(" + (getHand.indexOf(card) + 1) + ")" + " ")
        );

        if (Game.PICK_FROM_DECK_OR_COUNTER && !playerCanPickCardFromDeck) {
            System.out.println("\nEnter 'accept' to accept damage and pick from deck " +
                    "or play a card to counter ");
        } else if (!Game.PICK_FROM_DECK_OR_COUNTER && !playerCanPickCardFromDeck) {
            System.out.println("\nEnter the position of the card you want to play " +
                    "(1 - " + player.getHand().size() + ") " +
                    "or p to pick a card from the deck or 'pass' to pass your turn");
        } else {
            System.out.println("\nEnter the position of the card you want to play " +
                    "(1 - " + player.getHand().size() + ") or 'pass' to pass your turn");
        }
    }

    public static void getPile(List<Card> pile) {
        Deck.printDeck("Current pile", pile, 4);
        System.out.println("Pile size --> " + pile.size());
        System.out.println("Deck size --> " + deck.size());
        System.out.println("-".repeat(25));
        if (!pile.isEmpty()) {
            System.out.println("Top of the pile: " + pile.get(pile.size() - 1));
        } else {
            System.out.println("Pile is empty");
        }
        System.out.println("-".repeat(25));
    }

    public static void addToPile(Card card, ArrayList<Card> pile) {
        pile.add(card);
    }

    public static boolean isValidCard(Card card, ArrayList<Card> pile) {
        if (card == null) {
            return true; // pass turn
        }
        if (pile.isEmpty()) {
            return true; // Any card can be played if the pile is empty
        } else {
            var previousFace = pile.get(pile.size() - 1).face();
            var currentFace = card.face();

            var previousSuit = pile.get(pile.size() - 1).suit();
            var currentSuit = card.suit();

            var previousCardIsJoker_F = pile.get(pile.size() - 1).suit().equals(Suit.JOKER_F);
            var previousCardIsJoker_M = pile.get(pile.size() - 1).suit().equals(Suit.JOKER_M);

            var currentCardIsJoker_F = card.suit().equals(Suit.JOKER_F);
            var currentCardIsJoker_M = card.suit().equals(Suit.JOKER_M);

            var canPlayOnTopOfJoker_F = currentSuit.equals(Suit.HEARTS) || currentSuit.equals(Suit.DIAMONDS);
            var canPlayOnTopOfJoker_M = currentSuit.equals(Suit.SPADES) || currentSuit.equals(Suit.CLUBS);

            var canPlayJoker_F = previousSuit.equals(Suit.HEARTS) || previousSuit.equals(Suit.DIAMONDS);
            var canPlayJoker_M = previousSuit.equals(Suit.SPADES) || previousSuit.equals(Suit.CLUBS);

            if (previousCardIsJoker_F && canPlayOnTopOfJoker_F) {
                return true; // play hearts or diamonds on top of joker F
            }

            if (previousCardIsJoker_M && canPlayOnTopOfJoker_M) {
                return true; // play spades or clubs on top of joker M
            }

            if (currentCardIsJoker_F && canPlayJoker_F) {
                return true; // play joker F on top of hearts or diamonds
            }

            if (currentCardIsJoker_M && canPlayJoker_M) {
                return true; // play joker M on top of spades or clubs
            }

            return currentFace.equals(previousFace) || currentSuit.equals(previousSuit); // play if face or suit matches
        }
    }

    public static <T extends User> void checkIfPlayerWon(T user) {

        if (user.getHand().isEmpty()) {
            var scoreCount = user.getScore();
            scoreCount++;
            user.setScore(scoreCount);
            user.setScoreHistory();
            if (user instanceof Player) {
                System.out.println("Congratulations " + user.getName() + " you won!");
            } else {
                System.out.println(user.getName() + " won!");
            }

            EXIT_GAME = true;
            restartGame(deck, pile, new Scanner(System.in));
        }
    }
}
