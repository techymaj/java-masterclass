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
                iterator.remove();
                return card;
            }
        }

        randomizeThought();
        // If no valid card found in hand, pick a card from the deck
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

    private static void aiIsThinking() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            getLogger(AI.class.getName()).log(Level.INFO, e.getMessage(), e);
        }
    }

    public static void randomizeThought() {
        Random random = new Random();
        int thought = random.nextInt(10);
        switch (thought) {
            case 1 -> {
                System.out.println("Hold on, I'm thinking...");
                aiIsThinking();
            }
            case 2 -> {
                System.out.println("I am so winning this...");
                aiIsThinking();
            }
            case 3 -> {
                System.out.println("Strategizing...");
                aiIsThinking();
            }
            case 4 -> {
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
