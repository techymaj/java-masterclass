import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private int score;
    private List<Card> hand;
    private List<Integer> scoreHistory;

    public Player(String name) {
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

    public void playCard(List<Card> deck, int position) {
        var card = deck.get(position);
        deck.remove(card);
    }

    public List<Integer> getScoreHistory() {
        return this.scoreHistory;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
