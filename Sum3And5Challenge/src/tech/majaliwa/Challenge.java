package tech.majaliwa;

public class Challenge {
    public static void main(String[] args) {
        int sum = 0;
        int count = 0;

        for (int i = 1; i <= 1_000; i++) {

            // both 3 & 5
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println(i + " is divisible by both 3 & 5");
                sum = sum + i;
                count++;
            }

            if (count == 5)
                break;
        }

        System.out.println("The sum of the first five numbers divisible " +
                "by 3 & 5 is: " + sum);
    }
}
