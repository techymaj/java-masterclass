public class Challenge {
    public static void main(String[] args) {
        int playerPosition;
        playerPosition = calculateHighScorePosition(1_500);
        displayHighScorePosition("Jax",playerPosition);

        playerPosition = calculateHighScorePosition(1_000);
        displayHighScorePosition("Dek",playerPosition);

        playerPosition = calculateHighScorePosition(500);
        displayHighScorePosition("Harry", playerPosition);

        playerPosition = calculateHighScorePosition(100);
        displayHighScorePosition("Joe",playerPosition);

        playerPosition = calculateHighScorePosition(25);
        displayHighScorePosition("Poe",playerPosition);
    }
    static void displayHighScorePosition(String playerName, int playerPosition) {
        System.out.println(playerName + " managed to get into position " +
                playerPosition + " on the high score list.");
    }

    static int calculateHighScorePosition(int playerScore) {
        if (playerScore >= 1_000) {
            return 1;
        } else if (playerScore >= 500) {
            return 2;
        } else if (playerScore >= 100) {
            return 3;
        }
        return  4;
    }
}
