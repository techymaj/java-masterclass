package tech.majaliwa;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;

import static java.util.logging.Logger.*;
import static tech.majaliwa.Game.*;

public class AI extends User {

    public AI(String name) {
        super(name);
    }

    static void aI_s_Turn(AI ai, ArrayList<Card> pile) {
        enforcePassRule();
        while (true) {
            System.out.println("AI's turn");
            if (AI_TAKES_DAMAGE) {
                var face = pile.getLast();
                var currentFace = face.face();

                takeDamage(ai, deck, currentFace);
                break;
            }
            var cardPlayed = ai.playCard();
            var isValidCard = isValidCard(cardPlayed, pile);
            if (!isValidCard) {
                getPile(pile);
                continue;
            }
            System.out.println("*".repeat(25));
            System.out.println(ai.getName() + "'s remaining cards " + ai.getHand().size());
            System.out.println("*".repeat(25));
            if (cardPlayed != null) {
                addToPile(cardPlayed, pile);
                var opponentShouldPickFromDeck = ai.checkIfOpponentShouldPickFromDeck(cardPlayed);
                if (opponentShouldPickFromDeck) {
                    enforceDamageRules();
                    break;
                }
            }
            checkIfPlayerWon(ai);
            var canFollowCard = ai.checkIfCanFollowCard();
            if (canFollowCard) {
                continue;
            }
            break;
        }
    }

    public Card playCard() {
        var iterator = this.getHand().iterator();

        while (iterator.hasNext()) {
            var card = iterator.next();

            if (User.isValidCard(card, Game.pile)) {
                System.out.println(this.getName() + " played: " + card);
                AI_PICK_ONCE_FROM_DECK = true;
                playerCanPickCardFromDeck = true;
                iterator.remove();
                return card;
            }
        }

        if (getHand().size() > 7) {
            randomizeThought();
        }

        var pickFromDeckIfNoValidCardInHand = UserInterface.pickCard(deck);

        if (pickFromDeckIfNoValidCardInHand == null) {
            Game.reshuffleDeckAndContinuePlaying();
            return this.playCard();
        }

        if (AI_PICK_ONCE_FROM_DECK && AI_PICK_COUNT < 1) {
            this.getHand().add(pickFromDeckIfNoValidCardInHand);
            System.out.println(this.getName() + " picked a card from the deck");
            AI_PICK_ONCE_FROM_DECK = false;
            AI_PICK_COUNT++;
            playerCanPickCardFromDeck = true;
            return this.playCard();
        }

        if (User.isValidCard(pickFromDeckIfNoValidCardInHand, pile)) {
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
                System.out.println("Whatever it takes to win this, huh...");
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
