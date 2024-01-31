import java.util.ArrayList;
import java.util.List;

public class Deck {

    public enum SUIT {
        HEARTS(9829),
        SPADES(9824),
        CLUBS(9827),
        DIAMONDS(9830);

        private final int ascii;

        SUIT(int ascii) {
            this.ascii = ascii;
        }
    }

    public enum Face {

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

    public record Card(int rank, SUIT suit, Face face) {

        public static Card getNumericCard(SUIT suit, int rank) {
            return new Card(rank, suit, Face.getFace(rank));
        }

        public static Card getFaceCard(SUIT suit, Face face) {
            return new Card(face.ordinal(), suit, face);
        }

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
}