package tech.majaliwa;

public class Challenge {

    public static void main(String[] args) {
        int sum = sumDigits(-1);
        System.out.println("Sum of -1: " + sum);

        sum = sumDigits(1);
        System.out.println("Sum of 1: " + sum);

        sum = sumDigits(0);
        System.out.println("Sum of 0: " + sum);

        sum = sumDigits(123);
        System.out.println("Sum of 123: " + sum);

        sum = sumDigits(-123);
        System.out.println("Sum of -123: " + sum);

        sum = sumDigits(1234);
        System.out.println("Sum of 1234: " + sum);

        sum = sumDigits(1000);
        System.out.println("Sum of 1000: " + sum);
    }
    public static int sumDigits(int number) {

        int sum = 0;
        int lastDigit = 0;

        if (number < 0) {
            return -1;
        }

        if (number % 10 == number)
            return number;

        do {

            lastDigit = number % 10;
            sum += lastDigit;

            number = number / 10; // what remains

        } while (number != 0);

        return sum;
    }
}
