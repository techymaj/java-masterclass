package tech.majaliwa;

import java.util.ArrayList;
import java.util.List;

public class User implements UserInterface {

    private final String name;
    private final List<Integer> scoreHistory;
    private final ArrayList<Card> hand;
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

    public int getScore() {
        return this.score;
    }

    public void setScoreHistory() {
        this.scoreHistory.add(this.score);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setInitialHand(ArrayList<Card> deck) {
        var initialHand = deck.subList(0, 7);
        this.hand.addAll(initialHand);
        deck.subList(0, 7).clear();
    }

    public Card playCard(int position) {
        var iterator = this.hand.listIterator();
        var cardToPlay = this.hand.get(position - 1);

        while (iterator.hasNext()) {
            var card = iterator.next();
            if (card.equals(cardToPlay)) {
                System.out.println(this.name + " played: " + card);
                iterator.remove();
            }
        }

        return cardToPlay;
    }

    public boolean checkIfCanFollowCard() {
        var cardOnTopOfPile = Game.pile.get(Game.pile.size() - 1);
        var currentFace = cardOnTopOfPile.face();
        switch (currentFace) {
            case JACK, EIGHT -> {
                return true;
            }
        }
        return false;
    }
}
