import java.util.Scanner;

public class Student {

    public static void main(String[] args) {

        SACCO sacco = new SACCO("21/U/1234", 20_000);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            sacco.menu(); // Displays the menu
            System.out.println("Choose an option:");
            int option = scanner.nextInt();
            if (option == 1) {
                System.out.println("Enter the amount to withdraw:");
                int amountToWithdraw = scanner.nextInt();
                sacco.userInput(1, amountToWithdraw);
            } else if (option == 2) {
                System.out.println("Enter the amount to deposit:");
                int amountToDeposit = scanner.nextInt();
                sacco.userInput(2, amountToDeposit);
            } else {
                sacco.userInput(3, 0);
                break;
            }
        }
    }
}
