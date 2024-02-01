import java.util.ArrayList;
import java.util.List;

public class AI {
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

    public List<Card> getHand(List<Card> deck) {
        this.hand = deck.subList(0, 7);
        return this.hand;
    }

    public void playCard(Card card) {
        this.hand.remove(card);
    }

    public void showHand() {
        System.out.println(this.hand);
    }

    public void pickCard(List<Card> deck) {
        var cardToPick = deck.get(0);
        this.hand.add(cardToPick);
        deck.remove(cardToPick);
    }

    public List<Integer> getScoreHistory() {
        return this.scoreHistory;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
