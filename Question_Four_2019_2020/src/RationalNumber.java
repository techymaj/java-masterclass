public class RationalNumber {

    int numerator;
    int denominator;

    public RationalNumber(int numerator, int denominator) {
        this.numerator = numerator;
        if (denominator == 0) {
            System.out.println("Denominator cannot be zero");
            return;
        }
        this.denominator = denominator;
        System.out.println("The rational number is: " + this.numerator + " / " + this.denominator);
    }
//
//    public static void main(String[] args) {
//        RationalNumber rationalNumber = new RationalNumber(2, 0);
//    }
}
