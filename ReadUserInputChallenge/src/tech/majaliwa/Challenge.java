package tech.majaliwa;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Challenge {
    public static void main(String[] args) {

        double number;
        double sum = 0.0;
        int i = 1;

        Scanner scanner = new Scanner(System.in);

        while (i != 6) {

            System.out.println("Enter number #" + i + ": ");
            try {
                number = scanner.nextDouble();
                sum += number;
                i++;
            } catch (InputMismatchException e) {
                System.out.println("Invalid Value");
                scanner.next();
            }
        }

        System.out.println("sum = " + sum);
    }
}
