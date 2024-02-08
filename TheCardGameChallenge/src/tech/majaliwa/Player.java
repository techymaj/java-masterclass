package tech.majaliwa;

import java.util.ArrayList;
import java.util.Scanner;

import static tech.majaliwa.Game.*;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    static void player_s_Turn(Player player, ArrayList<Card> deck, ArrayList<Card> pile, Scanner scanner) {
        AI_TAKES_DAMAGE = false;

        do {
            userChoosesAction(player, playerCanPickCardFromDeck);
            var input = scanner.nextLine();
            var inputIsPickCard = input.equalsIgnoreCase("p");
            var inputIsAcceptDamage = input.equalsIgnoreCase("accept");

            if (inputIsAcceptDamage) {
                var face = pile.getLast();
                var currentFace = face.face();

                takeDamage(player, deck, currentFace);
                resetDamageRules();

                break;
            }

            if (inputIsPickCard && Game.PICK_FROM_DECK_OR_COUNTER) {
                continue;
            }

            if (inputIsPickCard && !playerCanPickCardFromDeck) {
                System.out.println("Pass your turn or play a card. Try again");
                continue;
            }
            if (inputIsPickCard) {
                pickCard(player);
                continue;
            } else {
                var inputIsPass = input.equalsIgnoreCase("pass");
                if (inputIsPass) {
                    if (playerCanPassAfterPickingOrPlayingCard) {
                        enforcePassRule();
                        System.out.println("You passed your turn");
                        break;
                    } else {
                        System.out.println("You can't pass without picking a card or playing one. Try again");
                        continue;
                    }
                } else {
                    if (cardPlayed(player, pile, input)) continue;
                }
            }
            break; // pass your turn
        } while (true);
    }


    @Override
    public Card playCard(int position) {
        AI_PICK_COUNT = 0; // reset pick rule for a.i
        var iterator = this.getHand().listIterator();
        var cardToPlay = this.getHand().get(position - 1);

        while (iterator.hasNext()) {
            var card = iterator.next();
            if (card.equals(cardToPlay)) {
                if (isValidCard(cardToPlay, Game.pile)) {
                    System.out.println(this.getName() + " played: " + card);
                    iterator.remove();
                }
            }
        }

        return cardToPlay;
    }

    static boolean cardPlayed(Player player, ArrayList<Card> pile, String input) {
        try {
            var numberIsGreaterThanHandSize = Integer.parseInt(input) > player.getHand().size();
            var numberIsLessThanHandSize = Integer.parseInt(input) < 1;
            var enteredNumberIsWrong = numberIsGreaterThanHandSize || numberIsLessThanHandSize;
            if (enteredNumberIsWrong) {
                System.out.println("Wrong card position. Try again");
                return true;
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input or wrong suit. Try again");
            return true;
        }

        return player.cardIsPlayed(player, pile, input);
    }

    private boolean cardIsPlayed(User user, ArrayList<Card> pile, String input) {
        var damageCardOnTop = checkIfDamageCard();
        var chosenCard = Integer.parseInt(input);
        var cardPlayed = user.playCard(chosenCard);
        var isValidCard = isValidCard(cardPlayed, pile);

        if (!isValidCard) {
            System.out.println("Invalid card. Try again");
            getPile(pile);
            return true;
        }

        if (damageCardOnTop) {
            var damageCountered = damageCountered(user, pile, cardPlayed);
            return !damageCountered;
        }

        System.out.println(user.getName() + "'s remaining cards " + user.getHand().size());
        addToPile(cardPlayed, pile);
        playerCanPassAfterPickingOrPlayingCard = true;
        checkIfPlayerWon(user);

        var canFollowCard = user.checkIfCanFollowCard();

        if (canFollowCard) {
            System.out.println("You can follow this card with another valid card");
            playerCanPickCardFromDeck = true;
            return true;
        }

        var opponentShouldPickFromDeck = user.checkIfOpponentShouldPickFromDeck(cardPlayed);

        if (opponentShouldPickFromDeck) {
            // a.i picks from deck
            AI_TAKES_DAMAGE = true;
            return false;
        }

        return false;
    }

    private static boolean damageCountered(User user, ArrayList<Card> pile, Card cardPlayed) {
        if (cardPlayed.face().equals(Face.TWO)) {
            AI_TAKES_DAMAGE = true;
            System.out.println(user.getName() + "'s remaining cards " + user.getHand().size());
            addToPile(cardPlayed, pile);
            checkIfPlayerWon(user);
            return true;
        }

        if (cardPlayed.face().equals(Face.THREE)) {
            AI_TAKES_DAMAGE = true;
            System.out.println(user.getName() + "'s remaining cards " + user.getHand().size());
            addToPile(cardPlayed, pile);
            checkIfPlayerWon(user);
            return true;
        }

        if (cardPlayed.face().equals(Face.JOKER)) {
            AI_TAKES_DAMAGE = true;
            System.out.println(user.getName() + "'s remaining cards " + user.getHand().size());
            addToPile(cardPlayed, pile);
            checkIfPlayerWon(user);
            return true;
        }

        if (cardPlayed.face().equals(Face.ACE) && cardPlayed.suit().equals(Suit.SPADES)) {
            System.out.println(user.getName() + "'s remaining cards " + user.getHand().size());
            addToPile(cardPlayed, pile);
            checkIfPlayerWon(user);
            return true;
        }
        return false;
    }
}
