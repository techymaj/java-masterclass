public class AI extends User {

    public AI(String name) {
        super(name);
    }

    public Card playCard() {
        var iterator = this.getHand().iterator();

        while (iterator.hasNext()) {
            var card = iterator.next();

            if (Main.isValidCard(this, card, Main.pile)) {
                System.out.println(this.getName() + " played: " + card);
                iterator.remove();
                return card;
            }
        }

        // If no valid card found in hand, pick a card from the deck
        System.out.println("AI is thinking...");
        var cardToPlay = UserInterface.pickCard(Main.deck);
        this.getHand().add(cardToPlay);
        System.out.println(this.getName() + " picked a card from the deck: " + cardToPlay);

        return null;
    }

}
