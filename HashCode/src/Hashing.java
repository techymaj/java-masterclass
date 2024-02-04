import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hashing {

    public static void main(String[] args) {

        String aText = "Hello";
        String bText = "Hello";
        String cText = String.join("l","He","lo");
        String dText = "He".concat("llo");
        String eText = "hello";

        List<String> hellos = List.of(aText, bText, cText, dText, eText);
        hellos.forEach(s -> System.out.println(s.hashCode()));
        System.out.println("=====================================");
        Set<String> helloSet = new HashSet<>(hellos);
        helloSet.forEach(s -> System.out.println(s.hashCode()));
        System.out.println("=====================================");
        System.out.println(helloSet);
        System.out.println(helloSet.size());

        PlayingCard aceHearts = new PlayingCard("Hearts", "Ace");
        PlayingCard kingClubs = new PlayingCard("Clubs", "King");
        PlayingCard queenDiamonds = new PlayingCard("Diamonds", "Queen");

        List<PlayingCard> cards = List.of(aceHearts, kingClubs, queenDiamonds);
        cards.forEach(c -> System.out.println(c + ": " + c.hashCode()));
        System.out.println("=====================================");

        Set<PlayingCard> cardSet = new HashSet<>();
        for (PlayingCard card : cards) {
            // add returns a boolean indicating whether the card was added
            if (!cardSet.add(card)) {
                System.out.println("Duplicate card: " + card);
            }
        }
        System.out.println(cardSet);
    }
}
