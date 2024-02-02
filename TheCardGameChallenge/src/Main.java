import java.util.*;

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
        Deck.printDeck("Current deck", deck, 4);
        System.out.println("Size of deck: " + deck.size());


        gameInSession(player, AI, deck, pile);

    }

    private static void gameInSession(Player player, AI ai, ArrayList<Card> deck, ArrayList<Card> pile) {
        var scanner = new Scanner(System.in);

        do {
            getPile(pile);
            // Player's turn
            while (true) {
                System.out.println("Your turn");
                System.out.println("Your hand: " + player.getHand());
                System.out.println("Enter the position of the card you want to play (1 - " + player.getHand().size() + ") or p to pick a card from the deck");
                var input = scanner.nextLine();
                if (input.equals("p")) {
                    var cardToPick = UserInterface.pickCard(deck);
                    player.getHand().add(cardToPick);
                    System.out.println("You picked: " + cardToPick);
                    System.out.println("Your hand: " + player.getHand());
                } else {
                    try {
                        var chosenCard = Integer.parseInt(input);
                        var cardPlayed = player.playCard(chosenCard);
                        var isValidCard = isValidCard(player, cardPlayed, pile);
                        if (!isValidCard) {
                            getPile(pile);
                            continue;
                        }
                        System.out.println(player.getName() + "'s remaining cards " + player.getHand().size());
                        addToPile(player, cardPlayed, pile);
                        checkIfPlayerWon(player);

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input");
                        continue;
                    }

                }
                break;
//                scanner.nextInt();


            }

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

        } while (!EXIT_GAME);
    }

//    public static <T extends User> void pickCardFromDeck(T user, ArrayList<Card> deck) {
//        var cardToPick = user.pickCard(deck);
//        user.getHand().add(cardToPick);
//    }


    public static void getPile(List<Card> pile) {
        System.out.println("Pile: " + pile);
    }

    public static <T extends User> void addToPile(T user, Card card, ArrayList<Card> pile) {
//        followFace(player, card, pile);
        pile.add(card);
    }

    public static <T extends User> boolean isValidCard(T user, Card card, ArrayList<Card> pile) {
        if (card == null) {
            return true;
        }
        if (pile.isEmpty()) {
            return true; // Any card can be played if the pile is empty
        } else {
            var previousFace = pile.get(pile.size() - 1).face();
            var currentFace = card.face();
            var previousSuit = pile.get(pile.size() - 1).suit();
            var currentSuit = card.suit();

            return currentFace.equals(previousFace) || currentSuit.equals(previousSuit);
        }
    }


    public static <T extends User> void checkIfMatchingFace(T user, Card card, ArrayList<Card> pile) {
//        pileIsEmpty(user, card, pile);
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
//        pileIsEmpty(user, card, pile);
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

    public static <T extends User> void checkIfPlayerWon(T user) {

        if (user.getHand().isEmpty()) {
            var scoreCount = user.getScore();
            scoreCount++;
            user.setScore(scoreCount);
            user.setScoreHistory();
            System.out.println(user.getName() + " has won the game!");
            System.out.println("Score: " + user.getScore());
            System.out.println("Score history: " + user.getScoreHistory());
            EXIT_GAME = true;
        }
    }
}
