import java.util.InputMismatchException;
import java.util.Scanner;

public class Challenge {
    public static void main(String[] args) {
        double min = 0.0;
        double max = 0.0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter minimum number: (Enter any letter to quit) ");
                min = scanner.nextDouble();
                System.out.println("Enter maximum number: (Enter any letter to quit) ");
                max = scanner.nextDouble();
            } catch (InputMismatchException ime) {
                break;
            }
            System.out.println("Minimum number: " + min);
            System.out.println("Maximum number: " + max);
            if (min > max) {
                System.out.println("Minimum #" + min + " is greater than Maximum #" + max);
            } else if (min == max) {
                System.out.println("The two numbers entered are equal");
            }
            System.out.println("-".repeat(100));
        }
    }
}
