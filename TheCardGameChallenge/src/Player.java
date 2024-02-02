public class Player extends User {

    public Player(String name) {
        super(name);
    }

    @Override
    public Card playCard(int position) {
        var iterator = this.getHand().listIterator();
        var cardToPlay = getCardToPlayInThis(position);

        while (iterator.hasNext()) {
            var card = iterator.next();
            if (card.equals(cardToPlay)) {
                if (Main.isValidCard(this, cardToPlay, Main.pile)) {
                    System.out.println(this.getName() + " played: " + card);
                    iterator.remove();
                }
            }
        }

        return cardToPlay;
    }

    private Card getCardToPlayInThis(int position) {
        Card card;
        try {
            card = this.getHand().get(position - 1);
            return card;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Wrong card position. Try Again");
            return this.getCardToPlayInThis(position);
        }
    }
}
