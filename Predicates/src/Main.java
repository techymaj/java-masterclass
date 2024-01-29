import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {

        String[] names = {"John", "Jane", "Jack", "Jill"};
        Predicate<String> firstLetter;
        Predicate<String> endLetter;

        int iteration = 1;
        for (var name : names) {
            firstLetter = s -> s.startsWith("Ja");
            endLetter = s -> s.endsWith("e");
            if (firstLetter.and(endLetter).test(name)) {
                System.out.println("Found " + name + "! after " + iteration + " iterations.");
                return;
            } else {
                System.out.println(
                        "#" + iteration + ": No name starts with Ja and ends with n!");
            }
            iteration++;
        }
    }
}
