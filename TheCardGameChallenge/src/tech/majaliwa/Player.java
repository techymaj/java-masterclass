package tech.majaliwa;

import static tech.majaliwa.Game.isValidCard;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    @Override
    public Card playCard(int position) {
        var iterator = this.getHand().listIterator();
        var cardToPlay = this.getHand().get(position - 1);

        while (iterator.hasNext()) {
            var card = iterator.next();
            if (card.equals(cardToPlay)) {
                if (isValidCard(cardToPlay, Game.pile)) {
                    System.out.println(this.getName() + " played: " + card);
                    iterator.remove();
                }
            }
        }

        return cardToPlay;
    }
}
