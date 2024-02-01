import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class AI implements PlayerInterface {
    private final String name;
    private int score;
    private List<Card> hand;
    private List<Integer> scoreHistory;

    public AI(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.scoreHistory = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    @Override
    public void setInitialHand(List<Card> deck) {
        this.hand = deck.subList(0, 7);
        deck.subList(0,7).clear();
    }

    public List<Card> getHand() {
        return this.hand;
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
    public void playCard(int position) {
        ListIterator<Card> iterator = this.hand.listIterator();
        while (iterator.hasNext()) {
            var card = iterator.next();
            if (iterator.nextIndex() == position) {
                System.out.println("You played: " + card);
                iterator.remove();
                return;
            }
        }
    }

    public List<Integer> getScoreHistory() {
        return this.scoreHistory;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
