import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<Card> cards;
    private boolean isCLASSIC;

    private Deck(boolean isCLASSIC) {
        if (isCLASSIC) {
            this.cards = new ArrayList<Card>(54);
        } else {
            this.cards = new ArrayList<Card>(52);
        }
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public static List<Card> createDeck(boolean isCLASSIC) {
        var deck = new Deck(isCLASSIC);
        var deckOfCards = deck.cards;

        for (int rank = 0; rank <= 12; rank++) {
            deckOfCards.add(new Card(Face.getFace(rank),Suit.HEARTS, Rank.values()[rank]));
            deckOfCards.add(new Card(Face.getFace(rank),Suit.SPADES, Rank.values()[rank]));
            deckOfCards.add(new Card(Face.getFace(rank),Suit.CLUBS, Rank.values()[rank]));
            deckOfCards.add(new Card(Face.getFace(rank),Suit.DIAMONDS, Rank.values()[rank]));
        }

        return deckOfCards;
    }

//    public static void shuffle() {
//        var shuffleDeck = new ArrayList<>(List.of(getCards()));
//        Collections.shuffle(shuffleDeck);
//    }

    public static void printDeck(String description, List<Card> deckOfCards, int rowCount) {
        System.out.println();
        System.out.println(description);

        if (rowCount < 1) {
            System.out.println("Enter a valid number of rows (number of rows > 0)");
            return;
        }

        // rows needed
        var rowsToPrint = deckOfCards.size() / rowCount;
        var set = 0;

        for (var card : deckOfCards) {
            System.out.printf("%-8s", card);
            set++;
            if (set == rowsToPrint) {
                System.out.println();
                set = 0;
            }
        }
    }

    public static void printDeck(String description, List<Card> deckOfCards) {
        System.out.println();
        System.out.println(description);

        // rows needed
        var rowsToPrint = deckOfCards.size() / 4;
        var set = 0;

        for (var card : deckOfCards) {
            System.out.printf("%-10s", card);
            set++;
            if (set == rowsToPrint) {
                System.out.println();
                set = 0;
            }
        }
    }
}
