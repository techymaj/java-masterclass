public class Challenge {

    public static void main(String[] args) {
        double height = convertToCentimeters(5,9);
        System.out.println("You are " + height + " cm tall.");

        height = convertToCentimeters(68);
        System.out.println("You are " + height + " cm tall.");
    }
    static double convertToCentimeters(int heightInInches) {
        return heightInInches * 2.54;
    }

    static double convertToCentimeters(int heightInFeet, int heightInInches) {
        int feet = heightInFeet * 12;
        int inches = heightInInches;
        return convertToCentimeters((feet + inches));
    }

}
