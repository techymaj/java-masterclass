import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
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

        var deck = Deck.createDeck(Rules.isCLASSIC);
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
        var pile = new ArrayList<Card>();

        System.out.println("Dealing cards...");
        player.setInitialHand(deck);
        AI.setInitialHand(deck);
        Deck.printDeck("Current deck", deck, 4);
        System.out.println("Size of deck: " + deck.size());

        gameInSession(player, AI, deck, pile);

    }

    private static void gameInSession(Player player, AI ai, ArrayList<Card> deck, ArrayList<Card> pile) {
        var scanner = new Scanner(System.in);
        var playerTurn = true;

        while (true) {
            if (playerTurn) {
                System.out.println("Your turn");
                System.out.println("Your hand: " + player.getHand());
                System.out.println("Pile: " + pile);
                System.out.println("Deck size: " + deck.size());
                System.out.println("Enter the position of the card you want to play (1 - 7)");
                var position = scanner.nextInt();
                var playedCard = player.playCard(position);
                checkIfMatchingFace(playedCard, pile);
                checkIfMatchingSuit(playedCard, pile);
                checkIfPlayerWon(player);
                playerTurn = false;
            }
            System.out.println("Your hand: " + player.getHand());
            break;
        }
    }

    public static void getPile(List<Card> pile) {
        System.out.println("Pile: " + pile);
    }

    public static void addToPile(Card card, List<Card> pile) {
        pile.add(card);
    }

    private static void pileIsEmpty(Card card, List<Card> pile) {
        if (pile.isEmpty()) {
            System.out.println("You can play this card");
            addToPile(card, pile);
        }
    }

    public static void checkIfMatchingFace(Card card, List<Card> pile) {
        pileIsEmpty(card, pile);
        if (card.face().equals(pile.get(pile.size() - 1).face())) {
            addToPile(card, pile);
        } else {
            System.out.println("You can't play this card");
        }
    }

    public static void checkIfMatchingSuit(Card card, List<Card> pile) {
        pileIsEmpty(card, pile);
        if (card.suit().equals(pile.get(pile.size() - 1).suit())) {
            addToPile(card, pile);
        } else {
            System.out.println("You can't play this card");
        }
    }

    public static <T extends User> void checkIfPlayerWon(T player) {
        if (player.getHand().isEmpty()) {
            System.out.println(player.getName() + " has won the game!");
            System.exit(0);
        }
    }
}
