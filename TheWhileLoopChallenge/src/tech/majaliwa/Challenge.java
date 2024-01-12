package tech.majaliwa;

public class Challenge {

    public static void main(String[] args) {

        int i = 6;
        int isEvenCount = 0;
        int isOddCount = 0;

        while (i <= 20) {

            if (isEvenNumber(i)) {
                isEvenCount++;
                System.out.println(i + " is even");
            } else {
                System.out.println("-".repeat(12) + i + " is odd");
                isOddCount++;
            }

            if (isEvenCount == 5)
                break;

            i++;
        }

        System.out.println("\nEven numbers: " + isEvenCount);
        System.out.println("Odd numbers: " + isOddCount);
    }
    public static boolean isEvenNumber(int number) {

        return number % 2 == 0;
    }
}
