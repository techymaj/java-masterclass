public class Equals {

    public static void main(String[] args) {

        String name = "Wilfried";
        String otherName = "Wilfried";

        // equals() compares the reference for objects
        if (name.equals(otherName)) {
            System.out.println("They are the same");
        } else {
            System.out.println("They are not the same");
        }

        int age = 22;
        int otherAge = 22;

        // can't use equlas() for primitive types
//        if (age.equals(otherAge)) {
//            System.out.println("They are the same");
//        } else {
//            System.out.println("They are not the same");
//        }

        Integer ageI = 22;
        Integer otherAgeI = 22;

        // can't use equlas() for primitive types
        if (ageI.equals(otherAgeI)) {
            System.out.println("They are the same");
        } else {
            System.out.println("They are not the same");
        }
    }
}
