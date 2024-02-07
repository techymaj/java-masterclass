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
                var face = pile.get(pile.size() - 1);
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
                pickCard(player, deck);
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
                    if (cardNotPlayed(player, pile, input)) continue;
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

    static boolean cardNotPlayed(Player player, ArrayList<Card> pile, String input) {
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

        return cardIsNotPlayed(player, pile, input);
    }
}
