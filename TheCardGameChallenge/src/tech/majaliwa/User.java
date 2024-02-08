package tech.majaliwa;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static tech.majaliwa.Game.*;

public class User implements UserInterface {

    private final String name;
    private final List<Integer> scoreHistory;
    private ArrayList<Card> hand;
    private int score;
    private boolean turn;

    public User(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.scoreHistory = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public int getScore() {
        return this.score;
    }

    public void setScoreHistory() {
        this.scoreHistory.add(this.score);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setInitialHand(ArrayList<Card> deck) {
        var initialHand = deck.subList(0, 7);
        this.hand.addAll(initialHand);
        deck.subList(0, 7).clear();
    }

    static void pickCard(User user) {
        var cardToPick = UserInterface.pickCard();

        if (cardToPick == null) {
            Game.reshuffleDeckAndContinuePlaying();
            return;
        }

        user.getHand().add(cardToPick);

        if (user instanceof Player) {
            System.out.println("You picked: " + cardToPick);
        } else {
            System.out.println(user.name + " picked: " + cardToPick);
        }
        enforcePickRule();
    }

    public static boolean isValidCard(Card card, ArrayList<Card> pile) {
        if (card == null) {
            return true; // pass turn
        }
        if (pile.isEmpty()) {
            return true; // Any card can be played if the pile is empty
        } else {
            var previousFace = pile.getLast().face();
            var currentFace = card.face();

            var previousSuit = pile.getLast().suit();
            var currentSuit = card.suit();

            var previousCardIsJoker_F = pile.getLast().suit().equals(Suit.JOKER_F);
            var previousCardIsJoker_M = pile.getLast().suit().equals(Suit.JOKER_M);

            var currentCardIsJoker_F = card.suit().equals(Suit.JOKER_F);
            var currentCardIsJoker_M = card.suit().equals(Suit.JOKER_M);

            var canPlayOnTopOfJoker_F = currentSuit.equals(Suit.HEARTS) || currentSuit.equals(Suit.DIAMONDS);
            var canPlayOnTopOfJoker_M = currentSuit.equals(Suit.SPADES) || currentSuit.equals(Suit.CLUBS);

            var canPlayJoker_F = previousSuit.equals(Suit.HEARTS) || previousSuit.equals(Suit.DIAMONDS);
            var canPlayJoker_M = previousSuit.equals(Suit.SPADES) || previousSuit.equals(Suit.CLUBS);

            if (previousCardIsJoker_F && canPlayOnTopOfJoker_F) {
                return true; // play hearts or diamonds on top of joker F
            }

            if (previousCardIsJoker_M && canPlayOnTopOfJoker_M) {
                return true; // play spades or clubs on top of joker M
            }

            if (currentCardIsJoker_F && canPlayJoker_F) {
                return true; // play joker F on top of hearts or diamonds
            }

            if (currentCardIsJoker_M && canPlayJoker_M) {
                return true; // play joker M on top of spades or clubs
            }

            return currentFace.equals(previousFace) || currentSuit.equals(previousSuit); // play if face or suit matches
        }
    }

    public static boolean checkIfDamageCard() {
        if (pile.isEmpty()) return false;

        var cardOnTop = pile.getLast();
        var currentFace = cardOnTop.face();

        if (Rules.JOKER_MODE) {
            switch (currentFace) {
                case TWO, THREE, JOKER -> {
                    playerCannotPickOrPass();
                    return true;
                }
            }
        } else {
            playerCannotPickOrPass();
            return Objects.requireNonNull(currentFace) == Face.TWO;
        }

        return false;
    }

    private static void playerCannotPickOrPass() {
        playerCanPassAfterPickingOrPlayingCard = false;
        playerCanPickCardFromDeck = false;
    }

    static void userChoosesAction(User user, boolean userCanPickCardFromDeck) {
        System.out.println("It's your turn");
        var getHand = user.getHand();
        System.out.print("Your hand: ");
        getHand.forEach(
                card -> System.out.print(card + "(" + (getHand.indexOf(card) + 1) + ")" + " ")
        );

        if (Game.PICK_FROM_DECK_OR_COUNTER && !userCanPickCardFromDeck) {
            System.out.println("\nEnter 'accept' to accept damage and pick from deck " +
                    "or play a card to counter ");
        } else if (!Game.PICK_FROM_DECK_OR_COUNTER && !userCanPickCardFromDeck) {
            System.out.println("\nEnter the position of the card you want to play " +
                    "(1 - " + user.getHand().size() + ") " +
                    "or p to pick a card from the deck or 'pass' to pass your turn");
        } else {
            System.out.println("\nEnter the position of the card you want to play " +
                    "(1 - " + user.getHand().size() + ") or 'pass' to pass your turn");
        }
    }

    public Card playCard(int position) {
        var iterator = this.hand.listIterator();
        var cardToPlay = this.hand.get(position - 1);

        while (iterator.hasNext()) {
            var card = iterator.next();
            if (card.equals(cardToPlay)) {
                System.out.println(this.name + " played: " + card);
                iterator.remove();
            }
        }

        return cardToPlay;
    }

    public boolean checkIfCanFollowCard() {
        var cardOnTopOfPile = pile.getLast();
        var currentFace = cardOnTopOfPile.face();
        switch (currentFace) {
            case JACK, EIGHT -> {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfOpponentShouldPickFromDeck(Card card) {
        var currentFace = card.face();

        switch (currentFace) {
            case TWO, THREE, JOKER -> {
                return true;
            }
        }
        return false;
    }

    static void takeDamage(User user, ArrayList<Card> deck, Face currentFace) {
        switch (currentFace) {
            case TWO -> {
                var deckIsEmpty = user.pickTwoCards(deck);
                if (deckIsEmpty == -1) {
                    reshuffleDeckAndContinuePlaying();
                    user.pickTwoCards(deck);
                }
            }
            case THREE -> {
                var deckIsEmpty = user.pickThreeCards(deck);
                if (deckIsEmpty == -1) {
                    reshuffleDeckAndContinuePlaying();
                    user.pickThreeCards(deck);
                }
            }
            case JOKER -> {
                var deckIsEmpty = user.pickFiveCards(deck);
                if (deckIsEmpty == -1) {
                    reshuffleDeckAndContinuePlaying();
                    user.pickFiveCards(deck);
                }
            }
        }
    }

    public int pickTwoCards(ArrayList<Card> deck) {
        if (deck.isEmpty()) {
            System.out.println("There is nothing left in the deck");
            return -1;
        }

        try {
            var picked = deck.subList(0, 2);
            ArrayList<Card> arrayList = new ArrayList<>(picked);

            this.hand.addAll(arrayList);

            if (this instanceof Player) {
                System.out.println("Picked 2");
            } else {
                System.out.println(this.name + " picked 2");
            }
            System.out.println();
            picked.clear();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Not enough cards in the deck");
            return -1;
        }

        return 1;
    }

    public int pickThreeCards(ArrayList<Card> deck) {
        if (deck.isEmpty()) {
            System.out.println("There is nothing left in the deck");
            return -1;
        }
        try {
            var picked = deck.subList(0, 3);
            ArrayList<Card> arrayList = new ArrayList<>(picked);

            this.hand.addAll(arrayList);

            if (this instanceof Player) {
                System.out.println("Picked 3");
            } else {
                System.out.println(this.name + " picked 3");
            }
            System.out.println();
            picked.clear();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Not enough cards in the deck");
            return -1;
        }

        return 1;
    }

    public int pickFiveCards(ArrayList<Card> deck) {
        if (deck.isEmpty()) {
            System.out.println("There is nothing left in the deck");
            return -1;
        }

        try {
            var picked = deck.subList(0, 5);
            ArrayList<Card> arrayList = new ArrayList<>(picked);

            this.hand.addAll(arrayList);

            if (this instanceof Player) {
                System.out.println("Picked 5");
            } else {
                System.out.println(this.name + " picked 5");
            }
            System.out.println();
            picked.clear();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Not enough cards in the deck");
            return -1;
        }

        return 1;
    }

    public static <T extends User> void checkIfPlayerWon(T user) {

        if (user.getHand().isEmpty()) {
            var scoreCount = user.getScore();
            scoreCount++;
            user.setScore(scoreCount);
            user.setScoreHistory();
            if (user instanceof Player) {
                System.out.println("Congratulations " + user.getName() + " you won!");
            } else {
                System.out.println(user.getName() + " won!");
            }

            Game.EXIT_GAME = true;
            restartGame(deck, pile, new Scanner(System.in));
        }
    }

    public static void getPile(List<Card> pile) {
        Deck.printDeck("Current pile", pile, 4);
        System.out.println("Pile size --> " + pile.size());
        System.out.println("Deck size --> " + deck.size());
        System.out.println("-".repeat(25));
        if (!pile.isEmpty()) {
            System.out.println("Top of the pile: " + pile.getLast());
        } else {
            System.out.println("Pile is empty");
        }
        System.out.println("-".repeat(25));
    }

    public static void addToPile(Card card, ArrayList<Card> pile) {
        pile.add(card);
    }
}
