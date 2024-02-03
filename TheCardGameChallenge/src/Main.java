import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static boolean EXIT_GAME = false;
    public static ArrayList<Card> pile = new ArrayList<>();
    public static ArrayList<Card> deck;
    public static int passCount = 0;
    public static int pickCount = 0;

    public static void main(String[] args) {
        System.out.println(Rules.WELCOME_MESSAGE);
        System.out.println();

        var scanner = setGameMode();
        do {
            if (scanner != null) {
                break;
            }
        } while (true);

        deck = Deck.createDeck(Rules.isCLASSIC);
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
    }

    private static Scanner setGameMode() {
        System.out.println("Do you want to play in classic mode? (y/n). Enter 'e' to exit the game");

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        switch (answer.toLowerCase()) {
            case "y", "yes" -> {
                Rules.isCLASSIC = true;
                System.out.println(Rules.CLASSIC);
            }
            case "e", "exit" -> {
                System.out.println("Have a good day!");
                System.exit(0);
            }
            case "n", "no" -> {
                Rules.isCLASSIC = false;
                System.out.println(Rules.NOT_CLASSIC);
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
            EXIT_GAME = false;
            deck.clear();
            pile.clear();
        } else if (input.equalsIgnoreCase("n")) {
            System.out.println("Thanks for playing!");
            System.exit(0);
        } else {
            System.out.println("Invalid input");
            restartGame(deck, pile, scanner);
        }
    }

    private static void player_s_Turn(Player player, ArrayList<Card> deck, ArrayList<Card> pile, Scanner scanner) {

        while (true) {
            playerChoosesAction(player, pickCount);
            var input = scanner.nextLine();
            if (input.equalsIgnoreCase("p") && pickCount == 1) {
                System.out.println("You can't pick a card without playing one. Try again");
                continue;
            }
            if (input.equalsIgnoreCase("p")) {
                var cardToPick = UserInterface.pickCard(deck);
                if (cardToPick == null) {
                    reshuffleDeckAndContinuePlaying();
                    continue;
                }

                player.getHand().add(cardToPick);
                System.out.println("You picked: " + cardToPick);
                pickCount++;
                passCount = 0; // now you can pass
                continue;

            } else if (input.equalsIgnoreCase("pass")) {
                if (passCount == 0) {
                    passCount++; // You can't pass until you pick a card or play one
                    pickCount = 0; // reset pick count
                    System.out.println("You passed your turn");
                    break;
                } else {
                    System.out.println("You can't pass without picking a card or playing one. Try again");
                    continue;
                }
            } else {
                try {
                    var numberIsGreaterThanHandSize = Integer.parseInt(input) > player.getHand().size();
                    var numberIsLessThanHandSize = Integer.parseInt(input) < 1;
                    var enteredNumberIsWrong = numberIsGreaterThanHandSize || numberIsLessThanHandSize;
                    if (enteredNumberIsWrong) {
                        System.out.println("Wrong card position. Try again");
                        continue;
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid input. Try again");
                    continue;
                }

                if (cardIsNotPlayed(player, pile, input)) continue; // pass turn
            }
            break;
        }
    }

    static void reshuffleDeckAndContinuePlaying() {
        System.out.println("There is nothing left in the deck");
        var getLastCard = Main.pile.get(Main.pile.size() - 1);
        System.out.println("Shuffling the pile...");
        Collections.shuffle(Main.pile);
        Main.pile.remove(getLastCard);
        Main.deck.addAll(Main.pile);
        Main.pile.clear();
        System.out.println("Pile shuffled and added to the deck");
        Main.pile.add(getLastCard);
        Deck.printDeck("Current pile", Main.pile, 1);
    }

    private static void aI_s_Turn(AI ai, ArrayList<Card> pile) {
        // AI's turn (if player successfully played a card)
        while (true) {
            System.out.println("AI's turn");
            var cardPlayed = ai.playCard();
            var isValidCard = isValidCard(ai, cardPlayed, pile);
            if (!isValidCard) {
                getPile(pile);
                continue;
            }
            System.out.println("*".repeat(25));
            System.out.println(ai.getName() + "'s remaining cards " + ai.getHand().size());
            System.out.println("*".repeat(25));
            if (cardPlayed != null) {
                addToPile(ai, cardPlayed, pile);
                passCount++; // Opponent can't pass until a card is picked or played
                pickCount = 0; // reset pick count for opponent
            }
            checkIfPlayerWon(ai);
            break;
        }
    }

    private static boolean cardIsNotPlayed(Player player, ArrayList<Card> pile, String input) {
        var chosenCard = Integer.parseInt(input);
        var cardPlayed = player.playCard(chosenCard);
        var isValidCard = isValidCard(player, cardPlayed, pile);
        if (!isValidCard) {
            getPile(pile);
            return true;
        }
        System.out.println(player.getName() + "'s remaining cards " + player.getHand().size());
        addToPile(player, cardPlayed, pile);
        passCount = 0;
        checkIfPlayerWon(player);
        return false;
    }

    static void playerChoosesAction(Player player, int pickCount) {
        System.out.println("Your turn");
        var getHand = player.getHand();
        System.out.print("Your hand: ");
        getHand.forEach(
                card -> System.out.print(card + "(" + (getHand.indexOf(card) + 1) + ")" + " ")
        );
        if (pickCount == 0) {
            System.out.println("\nEnter the position of the card you want to play (1 - " + player.getHand().size() + ") or p to pick a card from the deck or 'pass' to pass your turn");
        } else {
            System.out.println("\nEnter the position of the card you want to play (1 - " + player.getHand().size() + ") or 'pass' to your pass turn");
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

    public static <T extends User> void addToPile(T user, Card card, ArrayList<Card> pile) {
        pile.add(card);
    }

    public static <T extends User> boolean isValidCard(T user, Card card, ArrayList<Card> pile) {
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

            return currentFace.equals(previousFace) || currentSuit.equals(previousSuit); // play card if face or suit matches
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
            System.out.println(user.getName() + "'s score: " + user.getScore());
            user.getScoreHistory().add(user.getScore());
            System.out.println(user.getName() + "'s score history: " + user.getScoreHistory());

            EXIT_GAME = true;
            restartGame(deck, pile, new Scanner(System.in));
        }
    }
}
