package tech.majaliwa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static tech.majaliwa.AI.aI_s_Turn;
import static tech.majaliwa.Deck.printDeck;
import static tech.majaliwa.Player.player_s_Turn;
import static tech.majaliwa.User.getPile;

public class Game {

    public static boolean EXIT_GAME = false;
    public static ArrayList<Card> pile = new ArrayList<>();
    public static ArrayList<Card> deck;
    public static boolean playerCanPassAfterPickingOrPlayingCard = true;
    public static boolean playerCanPickCardFromDeck = true;
    public static boolean PICK_FROM_DECK_OR_COUNTER = false;
    public static boolean AI_PICK_ONCE_FROM_DECK = true;
    public static int AI_PICK_COUNT = 0;
    public static boolean AI_TAKES_DAMAGE = false;
    public static List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(Rules.WELCOME_MESSAGE);
        System.out.println();

        var scanner = setGameMode();
        System.out.println("Enter your player name");
        String playerName = scanner.nextLine();

        var player = new Player(playerName);
        var AI = new AI("AI");
        users.add(player);
        users.add(AI);

        gameUsers(player, AI);

        gameInSession(player, AI, deck, pile);
        scanner.close();
    }

    private static <T extends User> void gameUsers(T player, T ai) {
        deck = Deck.createDeck(Rules.JOKER_MODE);
        printDeck("Deck of Cards", deck, 4);
        System.out.println("Size of deck: " + deck.size());

        System.out.println();
        System.out.println("---------- Game in session ----------");
        System.out.println("You are now playing with the A.I");

        System.out.println("Shuffling deck...");
        Collections.shuffle(deck);

        System.out.println("Dealing cards...");
        player.setInitialHand(deck);
        ai.setInitialHand(deck);
        System.out.println("Size of deck: " + deck.size());
    }

    public void startGame() {
        main(new String[]{});
    }

    private static Scanner setGameMode() {
        System.out.println("Do you want to play in Joker mode? (y/n). Enter 'e' to exit the game");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        System.out.println();

        switch (answer.toLowerCase()) {
            case "y", "yes" -> {
                Rules.JOKER_MODE = true;
                System.out.println(Rules.JOKER);
            }
            case "e", "exit" -> {
                System.out.println("Have a good day!");
                System.exit(0);
            }
            case "n", "no" -> {
                Rules.JOKER_MODE = false;
                System.out.println(Rules.CLASSIC);
            }
            default -> {
                System.out.println("Invalid input. Enter 'e' to exit or 'y' or 'n' to continue");
                return setGameMode();
            }
        }

        return scanner;
    }

    private static <T extends  User> void gameInSession(T player, T ai, ArrayList<Card> deck, ArrayList<Card> pile) {
        var scanner = new Scanner(System.in);

        do {
            getPile(pile);
            if (player instanceof Player user) {
                player_s_Turn(user, deck, pile, scanner);
            }
            if (ai instanceof AI pc) {
                aI_s_Turn(pc, pile);
            }
        } while (!EXIT_GAME);

        restartGame(deck, pile, scanner);
        scanner.close();
    }

    static void restartGame(ArrayList<Card> deck, ArrayList<Card> pile, Scanner scanner) {
        System.out.println();
        System.out.println("Do you want to play again? (y/n)");
        var player = users.get(0);
        var ai = users.get(1);
        var input = scanner.nextLine();

        if (input.equalsIgnoreCase("y")) {
            System.out.println("Restarting game...");
            pile.clear();
            deck.clear();
            EXIT_GAME = false;
            setGameMode();
            gameUsers(player, ai);
            player.setInitialHand(deck);
            ai.setInitialHand(deck);
            gameInSession(player, ai, deck, pile);
        } else if (input.equalsIgnoreCase("n")) {
            System.out.println("Thanks for playing!");
            System.exit(0);
        } else {
            System.out.println("Invalid input");
            restartGame(deck, pile, scanner);
        }
    }

    static void enforceDamageRules() {
        PICK_FROM_DECK_OR_COUNTER = true;
        playerCanPickCardFromDeck = false;
        playerCanPassAfterPickingOrPlayingCard = false;
    }

    static void resetDamageRules() {
        PICK_FROM_DECK_OR_COUNTER = false;
        playerCanPickCardFromDeck = true;
        playerCanPassAfterPickingOrPlayingCard = true;
    }

    static void enforcePassRule() {
        playerCanPassAfterPickingOrPlayingCard = false; // player can't pass until a card is picked or played
        playerCanPickCardFromDeck = true; // reset pick count for player
    }

    static void enforcePickRule() {
        playerCanPickCardFromDeck = false; // player can't pick twice
        playerCanPassAfterPickingOrPlayingCard = true; // player can pass
    }

    static void reshuffleDeckAndContinuePlaying() {
        var getLastCard = pile.getLast();
        System.out.println("There is nothing left in the deck");
        System.out.println("Shuffling the pile...");
        Collections.shuffle(pile);
        pile.remove(getLastCard);
        deck.addAll(pile);
        pile.clear();
        System.out.println("Pile shuffled and added to the deck");
        pile.add(getLastCard);
        printDeck("Current pile", pile, 1);
    }
}
