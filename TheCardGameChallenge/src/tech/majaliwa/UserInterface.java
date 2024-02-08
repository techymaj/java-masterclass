package tech.majaliwa;

import static tech.majaliwa.Game.deck;

public interface UserInterface {
    static Card pickCard() {
        if (deck.isEmpty()) {
            return null;
        }
        var cardToPick = deck.getFirst();
        deck.removeFirst();
        return cardToPick;
    }
}
