package tech.majaliwa;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    private Deck(boolean isCLASSIC) {
        if (isCLASSIC) {
            this.cards = new ArrayList<>(54);
        } else {
            this.cards = new ArrayList<>(52);
        }
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public static ArrayList<Card> createDeck(boolean isCLASSIC) {
        var deck = new Deck(isCLASSIC);
        var deckOfCards = new ArrayList<>(deck.getCards());

        for (int rank = 0; rank <= 12; rank++) {
            deckOfCards.add(new Card(Face.getFace(rank),Suit.HEARTS, CardValue.values()[rank]));
            deckOfCards.add(new Card(Face.getFace(rank),Suit.SPADES, CardValue.values()[rank]));
            deckOfCards.add(new Card(Face.getFace(rank),Suit.CLUBS, CardValue.values()[rank]));
            deckOfCards.add(new Card(Face.getFace(rank),Suit.DIAMONDS, CardValue.values()[rank]));
        }
        if (isCLASSIC) {
            deckOfCards.add(new Card(Face.getFace(13),Suit.JOKER_F, CardValue.values()[13]));
            deckOfCards.add(new Card(Face.getFace(13),Suit.JOKER_M, CardValue.values()[13]));
        }

        return deckOfCards;
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
        System.out.println();
    }

}