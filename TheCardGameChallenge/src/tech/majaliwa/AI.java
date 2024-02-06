package tech.majaliwa;

import java.util.Random;
import java.util.logging.Level;

import static java.util.logging.Logger.*;

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
                Game.PICK_ONCE_FROM_DECK = true;
                iterator.remove();
                return card;
            }
        }

//        randomizeThought();
        var pickFromDeckIfNoValidCardInHand = UserInterface.pickCard(Game.deck);

        if (pickFromDeckIfNoValidCardInHand == null) {
            Game.reshuffleDeckAndContinuePlaying();
            return this.playCard();
        }

        if (Game.PICK_ONCE_FROM_DECK && Game.PICK_COUNT < 1) {
            this.getHand().add(pickFromDeckIfNoValidCardInHand);
            System.out.println(this.getName() + " picked a card from the deck");
            Game.PICK_ONCE_FROM_DECK = false;
            Game.PICK_COUNT++;
            return this.playCard();
        }

        if (Game.isValidCard(pickFromDeckIfNoValidCardInHand, Game.pile)) {
            System.out.println(this.getName() + " picked and played " + pickFromDeckIfNoValidCardInHand);
            this.getHand().remove(pickFromDeckIfNoValidCardInHand);
            return pickFromDeckIfNoValidCardInHand;
        }

        return null; // pass turn
    }

    private static void aiIsThinking() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            getLogger(AI.class.getName()).log(Level.INFO, e.getMessage(), e);
        }
    }

    public static void randomizeThought() {
        Random random = new Random();
        int thought = random.nextInt(40);
        switch (thought) {
            case 10 -> {
                System.out.println("Hold on, I'm thinking...");
                aiIsThinking();
            }
            case 20 -> {
                System.out.println("I am so winning this...");
                aiIsThinking();
            }
            case 30 -> {
                System.out.println("Strategizing...");
                aiIsThinking();
            }
            case 39 -> {
                System.out.println("Wait, i am planning...");
                aiIsThinking();
            }
            default -> {
                System.out.println("I'm starting to think you might win this one...");
                aiIsThinking();
            }
        }
    }
}
