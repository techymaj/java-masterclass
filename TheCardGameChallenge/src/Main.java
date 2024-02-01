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
        System.out.println("You are now playing with the A.I");

        System.out.println("Shuffling deck...");
        Collections.shuffle(deck);

        var player = new Player(playerName);
        var AI = new AI("AI");

        System.out.println("Dealing cards...");
        player.setInitialHand(deck);
        AI.setInitialHand(deck);
        Deck.printDeck("Current deck", deck, 4);
        System.out.println("Size of deck: " + deck.size());
        System.out.println("Woman Dancing: "); // Woman Dancing emoji
        System.out.println("Man Dancing: \uD83D\uDD7A");

        gameInSession(player, AI, deck);

    }

    private static void gameInSession(Player player, AI ai, ArrayList<Card> deck) {
    }

    public static void getPile(List<Card> pile) {
        System.out.println("Pile: " + pile);
    }
}
