import java.util.ArrayList;
import java.util.List;

public interface UserInterface {
    //    void setInitialHand(List<Card> deck);
//    Card pickCard(ArrayList<Card> deck);
    List<Card> pickTwoCards(ArrayList<Card> deck);

    List<Card> pickThreeCards(ArrayList<Card> deck);

    List<Card> pickFiveCards(ArrayList<Card> deck);

    //    Card playCard(int position);
    static Card pickCard(ArrayList<Card> deck) {
        if (deck.isEmpty()) {
            return null;
        }
        var cardToPick = deck.get(0);
        deck.remove(0);
        return cardToPick;
    }
}
