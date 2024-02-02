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
        var pickedCard = UserInterface.pickCard(Main.deck);
        this.getHand().add(pickedCard);
        System.out.println(this.getName() + " picked a card from the deck");

        if (Main.isValidCard(this, pickedCard, Main.pile)) {
            System.out.println(this.getName() + " picked and played");
            return pickedCard;
        }

//        if (pickCount == 2) {
//            this.pickTwoCards(Main.deck);
//        }
//
//        if (pickCount == 3) {
//            this.pickThreeCards(Main.deck);
//        }
//
//        if (pickCount == 5) {
//            this.pickFiveCards(Main.deck);
//        }

        return null; // pass turn
    }
}
