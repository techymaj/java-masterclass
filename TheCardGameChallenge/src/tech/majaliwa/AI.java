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
//            System.out.println(ai.getHand());

            var cardPlayed = ai.playCard();
            var isValidCard = isValidCard(cardPlayed, pile);
            if (!isValidCard) {
                getPile(pile);
                continue;
            }
            System.out.println("*".repeat(25));
            System.out.println(ai.getName() + "'s remaining cards " + ai.getHand().size());
            System.out.println("*".repeat(25));

            if (AI_TAKES_DAMAGE) {
                var face = pile.getLast();
                var currentFace = face.face();

                if (damageCountered(ai, pile, cardPlayed)) break;

                takeDamage(ai, deck, currentFace);
                resetDamageRules();
                break;
            }

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

    private static boolean damageCountered(AI ai, ArrayList<Card> pile, Card cardPlayed) {
        if (cardPlayed != null) {
            var damageCardOnTop = checkIfDamageCard();
            var counterCardPlayed = damageCountered(ai, pile, damageCardOnTop, cardPlayed);
            if (counterCardPlayed) return true;

            addToPile(cardPlayed, pile);
            var opponentShouldPickFromDeck = ai.checkIfOpponentShouldPickFromDeck(cardPlayed);
            if (opponentShouldPickFromDeck) {
                enforceDamageRules();
                return true;
            }
        }
        return false;
    }

    private static boolean damageCountered(User user, ArrayList<Card> pile, boolean damageCardOnTop, Card cardPlayed) {
        if (damageCardOnTop && cardPlayed.face().equals(Face.TWO)) {
            System.out.println(user.getName() + "'s remaining cards " + user.getHand().size());
            addToPile(cardPlayed, pile);
            playerCanPassAfterPickingOrPlayingCard = true;
            checkIfPlayerWon(user);
            return true;
        }

        if (damageCardOnTop && cardPlayed.face().equals(Face.THREE)) {
            System.out.println(user.getName() + "'s remaining cards " + user.getHand().size());
            addToPile(cardPlayed, pile);
            playerCanPassAfterPickingOrPlayingCard = true;
            checkIfPlayerWon(user);
            return true;
        }

        if (damageCardOnTop && cardPlayed.face().equals(Face.JOKER)) {
            System.out.println(user.getName() + "'s remaining cards " + user.getHand().size());
            addToPile(cardPlayed, pile);
            playerCanPassAfterPickingOrPlayingCard = true;
            checkIfPlayerWon(user);
            return true;
        }

        if (damageCardOnTop && cardPlayed.face().equals(Face.ACE) && cardPlayed.suit().equals(Suit.SPADES)) {
            System.out.println(user.getName() + "'s remaining cards " + user.getHand().size());
            addToPile(cardPlayed, pile);
            playerCanPassAfterPickingOrPlayingCard = true;
            checkIfPlayerWon(user);
            return true;
        }
        return false;
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

        var pickFromDeckIfNoValidCardInHand = UserInterface.pickCard();

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
            playerCanPickCardFromDeck = true;
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
