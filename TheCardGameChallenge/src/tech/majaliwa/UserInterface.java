package tech.majaliwa;

import java.util.ArrayList;

public interface UserInterface {
    static Card pickCard(ArrayList<Card> deck) {
        if (deck.isEmpty()) {
            return null;
        }
        var cardToPick = deck.get(0);
        deck.remove(0);
        return cardToPick;
    }
}
