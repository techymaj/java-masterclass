public class Main {
    public static void main(String[] args) {
        double doublevalue = 20D;
        double doubleTwo = 80D;
        double add = (doublevalue + doubleTwo) * 100;
        int remainder = (int) add % 40;
        boolean isZero = (remainder == 0) ? true : false;
        System.out.println("Remainder: " + isZero);
        if (!isZero)
            System.out.println("Got some remainder");
    }
}
