package tech.majaliwa;

public class NumberToWords {
    public static void main(String[] args) {
        numberToWords(11);
    }
    // write code here
    public static void numberToWords(int number) {

        int reversed = reverse(number);

        if (reversed < 0) System.out.println("Invalid Value");

        int lastDigit = 0;
        int remainingDigits = 0;
        int numberOfZeroes = 0;
        int original = number;

        do {
            // extract lastDigit
            lastDigit = reversed % 10;

            String word = switch (lastDigit) {
                case 0 -> "Zero";
                case 1 -> "One";
                case 2 -> "Two";
                case 3 -> "Three";
                case 4 -> "Four";
                case 5 -> "Five";
                case 6 -> "Six";
                case 7 -> "Seven";
                case 8 -> "Eight";
                case 9 -> "Nine";
                default -> "";
            };

            // remove lastDigit
            remainingDigits = reversed / 10;
            reversed = remainingDigits;

            // print word
            System.out.println(word);

        } while (reversed != 0);

        // print number of zeroes
        numberOfZeroes = getDigitCount(original) - getDigitCount(reversed); // problem here

         for (int i = 0; i < numberOfZeroes; i++) {
             System.out.println("Zero");
         }
    }

    public static int reverse(int number) {

        int reverse = 0;
        int lastDigit = 0;
        int remainingDigits = 0;

        do {
            lastDigit = number % 10;
            reverse = (reverse * 10) + lastDigit;
            remainingDigits = number / 10;
            number = remainingDigits;

        } while (number != 0);

        return reverse;
    }

    public static int getDigitCount(int number) {

        int count = 0;
        int remainingDigits = 0;

        if (number < 0) return -1;

        if (number == 0) return 1;

        do {

            remainingDigits = number / 10;
            number = remainingDigits;
            count++;

        } while (number != 0);

        return count;
    }
}
