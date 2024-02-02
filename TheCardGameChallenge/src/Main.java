import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static boolean EXIT_GAME = false;
    public static ArrayList<Card> pile = new ArrayList<>();
    public static ArrayList<Card> deck;

    public static void main(String[] args) {
        System.out.println(Rules.WELCOME_MESSAGE);
        System.out.println();

        System.out.println("Do you want to play in classic mode? (y/n) ");

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        if (answer.equals("y")) {
            Rules.isCLASSIC = true;
            System.out.println(Rules.CLASSIC);
        } else if (answer.equals("n")) {
            Rules.isCLASSIC = false;
            System.out.println(Rules.NOT_CLASSIC);
        } else {
            System.out.println("Invalid input");
            return;
        }

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

    private static void gameInSession(Player player, AI ai, ArrayList<Card> deck, ArrayList<Card> pile) {
        var scanner = new Scanner(System.in);

        do {
            getPile(pile);
            player_s_Turn(player, deck, pile, scanner);
            aI_s_Turn(ai, pile);
        } while (!EXIT_GAME);
    }

    private static void player_s_Turn(Player player, ArrayList<Card> deck, ArrayList<Card> pile, Scanner scanner) {
        var pickCount = 0;
        while (true) {
            playerChoosesAction(player, pickCount);
            var input = scanner.nextLine();
            if (input.equalsIgnoreCase("p")) {

                var cardToPick = UserInterface.pickCard(deck);
                if (cardToPick == null) {
                    reshuffleDeckAndContinuePlaying();
                    continue;
                }

                player.getHand().add(cardToPick);
                System.out.println("You picked: " + cardToPick);
                pickCount++;
                continue;

            } else if (input.isEmpty()) {
                System.out.println("You passed your turn");
                break; // pass turn
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
            System.out.println(ai.getName() + "'s remaining cards " + ai.getHand().size());
            if (cardPlayed != null) {
                addToPile(ai, cardPlayed, pile);
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
            System.out.println("\nEnter the position of the card you want to play (1 - " + player.getHand().size() + ") or p to pick a card from the deck or enter to pass turn");
        } else {
            System.out.println("\nEnter the position of the card you want to play (1 - " + player.getHand().size() + ") or enter to pass turn");
        }
    }

    public static void getPile(List<Card> pile) {
        Deck.printDeck("Current pile", pile, 4);
        System.out.println("-".repeat(20));
        if (!pile.isEmpty()) {
            System.out.println("Last played card: " + pile.get(pile.size() - 1));
        } else {
            System.out.println("Pile is empty");
        }
        System.out.println("-".repeat(20));
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
            // add confetti
            if (user instanceof Player) {
                System.out.println("You won the game!");
                JFrame frame = new JFrame();
                ImageIcon icon = new ImageIcon("agt.gif");
                JLabel label = new JLabel(icon);
                frame.add(label);
                frame.setDefaultCloseOperation
                        (JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            } else {
                System.out.println(user.getName() + " won the game!");
            }
            System.out.println(user.getName() + "'s score: " + user.getScore());
            System.out.println(user.getName() + "'s score history: " + user.getScoreHistory());
            EXIT_GAME = true;
        }
    }
}
