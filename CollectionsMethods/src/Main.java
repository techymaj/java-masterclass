import java.util.*;

public class Main {
    public static void main(String[] args) {

//        var deck = Deck.Card.getStandardDeck();
//        deck.sort(Comparator.comparing(Deck.Card::suit));
//        Deck.Card.printDeck("Deck of 4", deck);

        Deck.Card[] cardArray = new Deck.Card[12];
        Deck.Card aceOfHearts = Deck.Card.getFaceCard(Deck.SUIT.HEARTS, Deck.Face.ACE);
        Arrays.fill(cardArray, aceOfHearts);
//        Deck.Card.printDeck("Aced", Arrays.asList(cardArray));

        // initializing the ArrayList with 52 elements just sets the capacity to 52
        // the ArrayList is still empty
        List<Deck.Card> cards = new ArrayList<>(52);
        Collections.fill(cards, aceOfHearts);
        System.out.println(cards); // prints an empty list because the ArrayList, cards is still empty
        System.out.println("cards.size() = " + cards.size());

        List<Deck.Card> acesOfHearts = Collections.nCopies(12, aceOfHearts);
        Deck.Card.printDeck("Collected Aces", acesOfHearts, 2);

        Deck.Card kingOfClubs = Deck.Card.getFaceCard(Deck.SUIT.CLUBS, Deck.Face.KING);
        List<Deck.Card> kingsOfClubs = Collections.nCopies(10, kingOfClubs);
//        Deck.Card.printDeck("Collected Clubs", kingsOfClubs, 2);

        Collections.addAll(cards, cardArray);
//        cards.addAll(cardArray); // doesn't compile. the addAll() method takes a Collection as a parameter not an array
//        Deck.Card.printDeck("Collections.addAll()", cards, 1);

        List<Deck.Card> cardsCopy = new ArrayList<>(cards);
        System.out.println("cardsCopy = " + cardsCopy);
        Deck.Card.printDeck("cardsCopy:", cardsCopy, 2);
        Collections.fill(cardsCopy, kingOfClubs);
        Deck.Card.printDeck("cardsCopy after Collections.fill()", cardsCopy, 2);

        // cards -> destination
        // kingsOfClubs -> source
        // if source is bigger than destination, an IndexOutOfBoundsException is thrown
        // copies from source to destination without returning a copy
        Collections.copy(cards, kingsOfClubs);
        var copiedList = List.copyOf(cards); // returns an immutable copy of the list
        Deck.Card.printDeck("Collections.copy()", cards, 2);
        System.out.println("copiedList = " + copiedList);
    }
}
