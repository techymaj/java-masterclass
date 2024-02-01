import java.util.ArrayList;
import java.util.List;

public interface PlayerInterface {
//    void setInitialHand(List<Card> deck);
    Card pickCard(ArrayList<Card> deck);
    List<Card> pickTwoCards(ArrayList<Card> deck);
    List<Card> pickThreeCards(ArrayList<Card> deck);
    List<Card> pickFiveCards(ArrayList<Card> deck);
//    Card playCard(int position);
}
