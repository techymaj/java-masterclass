import java.util.Collections;

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
        try {
            var pickedCard = UserInterface.pickCard(Main.deck);
            this.getHand().add(pickedCard);
            System.out.println(this.getName() + " picked a card from the deck");

            if (Main.isValidCard(this, pickedCard, Main.pile)) {
                System.out.println(this.getName() + " picked and played " + pickedCard);
                this.getHand().remove(pickedCard);
                return pickedCard;
            }
        } catch (NullPointerException e) {
            System.out.println("There is nothing left in the deck");
            var getLastCard = Main.pile.get(Main.pile.size() - 1);
            System.out.println("Shuffling the pile...");
            Collections.shuffle(Main.pile);
            Main.pile.remove(getLastCard);
            Main.deck.addAll(Main.pile);
            Main.pile.clear();
            System.out.println("Pile shuffled and added to the deck");
            Main.pile.add(getLastCard);
            Deck.printDeck("Current pile", Main.pile, 1);
            // try to pick a card from the deck again
            return this.playCard();
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
