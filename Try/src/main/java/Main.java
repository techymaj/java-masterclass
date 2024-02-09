import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    enum SUIT {
        HEARTS(9829),
        SPADES(9824),
        CLUBS(9827),
        DIAMONDS(9830);

        private final int ascii;

        SUIT(int ascii) {
            this.ascii = ascii;
        }
    }

    enum Face {

        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
        NINE("9"),
        TEN("10"),
        JACK("J"),
        QUEEN("Q"),
        KING("K"),
        ACE("A");

        private final String face;

        Face(String face) {
            this.face = face;
        }

        public static Face getFace(int rank) {
            return Face.values()[rank];
        }
    }

    record Card(int rank, SUIT suit, Face face) {

        public static Card getNumericCard(SUIT suit, int rank) {
            return new Card(rank, suit, Face.getFace(rank));
        }

        public static Card getFaceCard(SUIT suit, Face face) {
            return new Card(face.ordinal(), suit, face);
        }

        public static void printDeck(String description, List<Card> deckOfCards, int rowCount) {
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

        public static List<Card> getStandardDeck() {
            List<Card> deckOfCards = new ArrayList<>(52);
            for (int rank = 0; rank <= 12; rank++) {
                deckOfCards.add(new Card(rank, SUIT.HEARTS, Face.getFace(rank)));
                deckOfCards.add(new Card(rank, SUIT.SPADES, Face.getFace(rank)));
                deckOfCards.add(new Card(rank, SUIT.CLUBS, Face.getFace(rank)));
                deckOfCards.add(new Card(rank, SUIT.DIAMONDS, Face.getFace(rank)));
            }

            return deckOfCards;
        }

        public String toString() {
            return face.face + (char) suit.ascii + "(" + rank + ")";
        }
    }

    public static void main(String[] args) {

        var deckOfCards = Card.getStandardDeck();

        System.out.println("---------------Helper Methods-----------------");

        System.out.println("getNumericCard()");
        Card myCard1 = Card.getNumericCard(SUIT.HEARTS, 2);
        System.out.println(myCard1);
        System.out.println();

        System.out.println("getFaceCard()");
        Card myCard2 = Card.getFaceCard(SUIT.CLUBS, Face.ACE);
        System.out.println(myCard2);
        System.out.println();

        System.out.println("printDeck()");
        Card.printDeck("A random deck", deckOfCards, 12);
        System.out.println();

        System.out.println("A random deck of 4 default rows");
        Card.printDeck("Four rows", deckOfCards);

        deckOfCards.sort(Comparator.comparing(Card::suit));
        System.out.println();
        Card.printDeck("Sorted by suit (default rows)", deckOfCards);
        System.out.println();

        deckOfCards.sort(Comparator.comparing(Card::face));
        Card.printDeck("Sorted by face (custom rows)", deckOfCards, 12);
    }
}