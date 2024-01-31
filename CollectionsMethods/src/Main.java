import java.util.*;

import static java.util.Collections.*;

public class Main {
    public static void main(String[] args) {

        var deck = Deck.Card.getStandardDeck();
        deck.sort(Comparator.comparing(Deck.Card::suit));
        Deck.Card.printDeck("Deck of 4", deck);

        Deck.Card[] cardArray = new Deck.Card[12];
        Deck.Card aceOfHearts = Deck.Card.getFaceCard(Deck.SUIT.HEARTS, Deck.Face.ACE);
        Arrays.fill(cardArray, aceOfHearts);
        Deck.Card.printDeck("Aced", Arrays.asList(cardArray));

        // initializing the ArrayList with 52 elements just sets the capacity to 52
        // the ArrayList is still empty
        List<Deck.Card> cards = new ArrayList<>(52);
        fill(cards, aceOfHearts);
        System.out.println(cards); // prints an empty list because the ArrayList, cards is still empty
        System.out.println("cards.size() = " + cards.size());

        List<Deck.Card> acesOfHearts = Collections.nCopies(12, aceOfHearts);
        Deck.Card.printDeck("Collected Aces", acesOfHearts, 2);

        Deck.Card kingOfClubs = Deck.Card.getFaceCard(Deck.SUIT.CLUBS, Deck.Face.KING);
        List<Deck.Card> kingsOfClubs = Collections.nCopies(10, kingOfClubs);
//        Deck.Card.printDeck("Collected Clubs", kingsOfClubs, 2);

        addAll(cards, cardArray);
//        cards.addAll(cardArray); // doesn't compile. the addAll() method takes a Collection as a parameter not an array
        Deck.Card.printDeck("Collections.addAll()", cards, 1);

        List<Deck.Card> cardsCopy = new ArrayList<>(cards);
        System.out.println("cardsCopy = " + cardsCopy);
        Deck.Card.printDeck("cardsCopy:", cardsCopy, 2);
        fill(cardsCopy, kingOfClubs);
        Deck.Card.printDeck("cardsCopy after Collections.fill()", cardsCopy, 2);

        // cards -> destination
        // kingsOfClubs -> source
        // if source is bigger than destination, an IndexOutOfBoundsException is thrown
        // copies from source to destination without returning a copy
        copy(cards, kingsOfClubs);
        var copiedList = List.copyOf(cards); // returns an immutable copy of the list
        Deck.Card.printDeck("Collections.copy()", cards, 2);
        System.out.println("copiedList = " + copiedList);

        deck = Deck.Card.getStandardDeck();
        deck.sort(Comparator.comparing(Deck.Card::suit));
        Deck.Card.printDeck("Deck of 4", deck);
        Collections.shuffle(deck);
        Deck.Card.printDeck("Shuffled deck of 4 rows", deck);
        reverse(deck);
        Deck.Card.printDeck("Reversed deck of 4 rows", deck);
//        Collections.sort(deck); // Card doesn't implement Comparable<Card> so this doesn't work
        // reengineered for backwards compatibility
        sort(deck, Comparator.comparing(Deck.Card::rank).thenComparing(Deck.Card::suit).reversed());

        Deck.Card.printDeck("Deck of 13", deck, 13);
        List<Deck.Card> kings = new ArrayList<>(deck.subList(4, 8));
        Deck.Card.printDeck("Kings", kings, 1);

        List<Deck.Card> tens = new ArrayList<>(deck.subList(16, 20));
        Deck.Card.printDeck("Tens", tens, 1);

//        Collections.shuffle(deck); // shuffles the deck
        // indexOfSubList method returns the index of the first occurrence of the specified subList in the specified list
        int subListIndex = Collections.indexOfSubList(deck, kings);
        // remember that the deck list was shuffled and the elements in the subList must be contiguously found in the full list
        System.out.println("subListIndex = " + subListIndex); // prints -1 because there is no such occurrence of the kings subList in the deck list
        System.out.printf("Contains kings? %s%n", deck.containsAll(kings) ? "Yes" : "No");

        // disjoint method
        // returns true if the two collections have no elements in common
        // returns false if the two collections have at least one element in common
        System.out.printf("Disjoint? %s%n", Collections.disjoint(deck, kings) ? "Yes" : "No");
        System.out.printf("Disjoint? %s%n", Collections.disjoint(kings, tens) ? "Yes" : "No");

//        deck.sort(Comparator.comparing(Deck.Card::suit));
//        Deck.Card.printDeck("Deck of 4", deck);

        // binarySearch method
        // returns the index of the element if found
        // returns a negative number if the element is not found
        // Deck of tens
        var deck2 = Deck.Card.getStandardDeck();
        // Your list must be sorted before you can execute binarySearch() on it
        var sortingAlgorithm = Comparator.comparing(Deck.Card::face)
                .thenComparing(Deck.Card::suit)
                .thenComparing(Deck.Card::rank);
//        deck2.sort(sortingAlgorithm);
        Deck.Card.printDeck("Deck2 of 4", deck2);
        Deck.Card tenOfHearts = Deck.Card.getFaceCard(Deck.SUIT.HEARTS, Deck.Face.TEN);
        int foundIndex = Collections.binarySearch(deck2, tenOfHearts, sortingAlgorithm);
        System.out.println("foundIndex = " + foundIndex);
        // the indexOf() can find the index of the element in the list. sorted or not
        System.out.println("deck2.get(foundIndex) = " + deck2.indexOf(tenOfHearts));
        System.out.println("deck2.get(foundIndex) = " + deck2.get(foundIndex));

        Deck.Card tenOfClubs = Deck.Card.getFaceCard(Deck.SUIT.CLUBS, Deck.Face.TEN);
        // deck2 -> list
        // tenOfHearts -> old value
        // tenOfClubs -> new value
        var isReplaced = Collections.replaceAll(deck2.subList(32, 36), tenOfHearts, tenOfClubs);
        System.out.println("isReplaced? " + (isReplaced ? "Yes" : "No"));
        Deck.Card.printDeck("Deck2 of 4 after replacing hearts with clubs", deck2.subList(32, 36), 1);

        System.out.println("Frequency of tenOfClubs = " + Collections.frequency(deck2, tenOfClubs));
        System.out.println("Best Card = " + Collections.max(deck2, sortingAlgorithm));
        System.out.println("Worst Card = " + Collections.min(deck2, sortingAlgorithm));

        // sort by suit then rank
        var sortBySuitThenRank = Comparator.comparing(Deck.Card::suit).thenComparing(Deck.Card::rank);
        deck.sort(sortBySuitThenRank);
        Deck.Card.printDeck("Deck2 of 4 sorted by suit then rank", deck);

        List<Deck.Card> copied = new ArrayList<>(deck2.subList(0, 13));
        // rotate method
        // back to front if it's a positive number
        // front to back if it's a negative number
        rotate(copied, -2);
        Deck.Card.printDeck("Un rotated deck", deck2.subList(0, 13), 1);
        Deck.Card.printDeck("Rotated deck", copied, 1);

        copied = new ArrayList<>(deck2.subList(0, 13));
        for (int i = 0; i < copied.size() / 2; i++) {
            swap(copied, i, copied.size() - 1 - i);
        }
        Deck.Card.printDeck("Manual reverse", copied, 1);

        copied = new ArrayList<>(deck2.subList(0, 13));
        reverse(copied);
        Deck.Card.printDeck("Collections.reverse()", copied, 1);


    }
}
