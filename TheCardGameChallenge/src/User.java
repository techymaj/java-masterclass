import java.util.ArrayList;
import java.util.List;

public class User {

    private final String name;
    private final List<Integer> scoreHistory;
    private ArrayList<Card> hand;
    private int score;

    public User(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.scoreHistory = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public ArrayList<Card> getInitialHand(ArrayList<Card> deck) {
        var initialHand = deck.subList(0,7);
        this.hand.addAll(initialHand);
        initialHand.clear();
        return (ArrayList<Card>) initialHand;
    }

    public int getScore() {
        return this.score;
    }

    public List<Integer> getScoreHistory() {
        return this.scoreHistory;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setInitialHand(ArrayList<Card> deck) {
        var initialHand = deck.subList(0,7);
        this.hand.addAll(initialHand);
        deck.subList(0,7).clear();
    }

    public Card playCard(int position) {
        var iterator = this.hand.listIterator();
        var cardToPlay = this.hand.get(position - 1);

        while (iterator.hasNext()) {
            var card = iterator.next();
            if (card.equals(cardToPlay)) {
                System.out.println("You played: " + card);
                iterator.remove();
            }
        }

        return cardToPlay;
    }
}
