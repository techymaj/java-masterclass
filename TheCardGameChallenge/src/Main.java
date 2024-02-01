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
        var random = new Random();

        while (true) {
            System.out.println("Pile: " + pile);
            // Player's turn
            if (playerTurn) {
                System.out.println("Your turn");
                System.out.println("Your hand: " + player.getHand());
                System.out.println("Enter the position of the card you want to play (1 - " + player.getHand().size() + ")");
                var position = scanner.nextInt();
                var playedCard = player.playCard(position);
                checkIfMatchingFace(player, playedCard, pile);
                checkIfMatchingSuit(player, playedCard, pile);
                checkIfPlayerWon(player);
                playerTurn = false; // Switch to AI's turn only if player successfully played a card
            }

            // AI's turn (if player successfully played a card)
            if (!playerTurn) {
                System.out.println("AI's turn");
                random = new Random();
                var randomNum = random.nextInt(0, ai.getHand().size() - 1);
//                System.out.println("A.I played card at position: " + randomNum);
                var cardPlayed = ai.playCard(randomNum + 1);
                System.out.println(ai.getName() + "'s remaining cards " + ai.getHand().size());
                checkIfMatchingFace(ai, cardPlayed, pile);
                checkIfMatchingSuit(ai, cardPlayed, pile);
                checkIfPlayerWon(ai);
                playerTurn = true; // Switch back to player's turn
            }
        }
    }


    public static void getPile(List<Card> pile) {
        System.out.println("Pile: " + pile);
    }

    public static <T extends User> void addToPile(T user, Card card, ArrayList<Card> pile) {
//        followFace(player, card, pile);
        pile.add(card);
    }

    private static <T extends User> void pileIsEmpty(T user, Card card, ArrayList<Card> pile) {
        if (pile.isEmpty()
                || !card.face().equals(pile.get(pile.size() - 1).face())
                || !card.suit().equals(pile.get(pile.size() - 1).suit())
        ) {
            System.out.println("You can play this card");
            addToPile(user, card, pile);
        }
    }


    public static <T extends User> void checkIfMatchingFace(T user, Card card, ArrayList<Card> pile) {
        pileIsEmpty(user, card, pile);
//        while (true) {
//            if (card.face().equals(pile.get(pile.size() - 1).face()) && pile.size() > 1) {
//                addToPile(user, card, pile);
//                break;
//            } else if (pile.size() > 1){
//                System.out.println("You can't play this card");
//            }
//        }
    }

    public static <T extends User> void checkIfMatchingSuit(T user, Card card, ArrayList<Card> pile) {
        pileIsEmpty(user, card, pile);
//        while (true) {
//            if (card.suit().equals(pile.get(pile.size() - 1).suit()) && pile.size() > 1) {
//                addToPile(user, card, pile);
//                break;
//            } else if (pile.size() > 1){
//                System.out.println("You can't play this card");
//            }
//        }
    }

//    public static <T extends User> void followFace(T player, Card cardPlayed, List<Card> pile) {
//        var face = cardPlayed.face();
//
//        if (face.equals(Face.JACK)) {
//            followJack(player, cardPlayed, pile);
//        }
//    }
//
//    public static <T extends User> void followJack(T player, Card card, List<Card> pile) {
//        Scanner scanner = new Scanner(System.in);
//        var jack = card.face().equals(Face.JACK);
//        if (jack) {
//            System.out.println("Add to card: " + card);
//            System.out.println("Your hand: " + player.getHand());
//            System.out.println("Enter the position of the card you want to play (1 - 7)");
//            var chosenCard = scanner.nextInt();
//            var playedCard = player.playCard(chosenCard);
//            addToPile(player, playedCard, pile);
//        }
//    }

    public static <T extends User> void checkIfPlayerWon(T player) {
        if (player.getHand().isEmpty()) {
            System.out.println(player.getName() + " has won the game!");
            System.exit(0);
        }
    }
}
