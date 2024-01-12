package tech.majaliwa;

public class DiagonalStar {
    // write code here
    public static void main(String[] args) {
        printSquareStar(5);
    }
    public static void printSquareStar(int number) {

        if (number < 5) System.out.println("Invalid Value");

        for (int i = 0; i < number; i++) {
            System.out.println("*".repeat(number));
        }
    }
}
