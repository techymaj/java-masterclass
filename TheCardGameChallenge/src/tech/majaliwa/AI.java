package tech.majaliwa;

public class AI extends User {

    public AI(String name) {
        super(name);
    }

    public Card playCard() {
        var iterator = this.getHand().iterator();

        while (iterator.hasNext()) {
            var card = iterator.next();

            if (Game.isValidCard(card, Game.pile)) {
                System.out.println(this.getName() + " played: " + card);
                iterator.remove();
                return card;
            }
        }

        // If no valid card found in hand, pick a card from the deck
        System.out.println("AI is thinking...");
        var pickedCard = UserInterface.pickCard(Game.deck);

        if (pickedCard == null) {
            Game.reshuffleDeckAndContinuePlaying();
            return this.playCard();
        }

        this.getHand().add(pickedCard);
        System.out.println(this.getName() + " picked a card from the deck");

        if (Game.isValidCard(pickedCard, Game.pile)) {
            System.out.println(this.getName() + " picked and played " + pickedCard);
            this.getHand().remove(pickedCard);
            return pickedCard;
        }

        return null; // pass turn
    }
}
