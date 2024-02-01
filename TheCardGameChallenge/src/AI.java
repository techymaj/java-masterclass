import java.util.ArrayList;
import java.util.List;

public class AI extends User implements PlayerInterface {

    public AI(String name) {
        super(name);
    }

    @Override
    public Card pickCard(ArrayList<Card> deck) {
        if (deck.isEmpty()) {
            System.out.println("There is nothing left in the deck");
            return null;
        }
        var cardToPick = deck.get(0);
        deck.remove(0);
        return cardToPick;
    }

    @Override
    public List<Card> pickTwoCards(ArrayList<Card> deck) {
        if (deck.isEmpty()) {
            System.out.println("There is nothing left in the deck");
            return null;
        }
        var cardToPick = deck.subList(0, 2);
        deck.subList(0, 2).clear();
        return cardToPick;
    }

    @Override
    public List<Card> pickThreeCards(ArrayList<Card> deck) {
        if (deck.isEmpty()) {
            System.out.println("There is nothing left in the deck");
            return null;
        }
        var cardToPick = deck.subList(0, 3);
        deck.subList(0, 3).clear();
        return cardToPick;
    }

    @Override
    public List<Card> pickFiveCards(ArrayList<Card> deck) {
        if (deck.isEmpty()) {
            System.out.println("There is nothing left in the deck");
            return null;
        }
        var cardToPick = deck.subList(0, 5);
        deck.subList(0, 5).clear();
        return cardToPick;
    }

    @Override
    public Card playCard(int position) {
        return null;
    }

//    @Override
//    public Card playCard(int position) {
//        var iterator = this.getHand().listIterator();
//        while (iterator.hasNext()) {
//            var card = iterator.next();
//            if (iterator.nextIndex() == position) {
//                System.out.println("You played: " + card);
//                iterator.remove();
//                return;
//            }
//        }
//    }

}
