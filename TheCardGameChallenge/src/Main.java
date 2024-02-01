import Exceptions.NotInClassicModeException;

import java.util.*;

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

        var deck = Deck.createDeck(true);
        Deck.printDeck("Deck of Cards", deck, 4);

        System.out.println("Enter your player name");
        String playerName = scanner.nextLine();

        System.out.println();
        System.out.println("You are now playing with the A.I");

        var player = new Player(playerName);
        var AI = new AI("AI");

        System.out.println("Shuffling deck...");
        Collections.shuffle(deck);

        System.out.println("Dealing cards...");
        var playerHand = new ArrayList<>(player.getHand(deck));
        var aiHand = new ArrayList<>(AI.getHand(deck));

        List<Card> pile = new ArrayList<>();

        System.out.println("Your hand: " + playerHand);

        player.playCard(playerHand, 0);

        System.out.println("You played: " + playerHand.get(0));
        pile.add(playerHand.get(0));
        getPile(pile);

        Random random = new Random();
        int randomCard = random.nextInt(0, (aiHand.size()));
        System.out.println("A.I played: " + aiHand.get(randomCard));
        pile.add(aiHand.get(randomCard));
        getPile(pile);

        System.out.println("Your hand: " + playerHand);

    }

    public static void getPile(List<Card> pile) {
        System.out.println("Pile: " + pile);
    }
}
