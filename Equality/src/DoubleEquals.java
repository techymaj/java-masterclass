public class DoubleEquals {

    public static void main(String[] args) {

        String name = "Wilfried";
        String otherName = "Wilfried";

        // == compares the reference for objects
        if (name == otherName) {
            System.out.println("They are the same");
        } else {
            System.out.println("They are not the same");
        }

        int age = 22;
        int otherAge = 22;

        // == compares the actual values for primitive types
        if (age == otherAge) {
            System.out.println("They are the same");
        } else {
            System.out.println("They are not the same");
        }
    }
}
