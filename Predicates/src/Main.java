import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {

        String[] names = {"John Mason", "Jane Foster", "Jack Ma", "Jill"};

        findHer(names);

        System.out.println();
        System.out.println("Arrays.sort() Sorting by length...");
        Arrays.sort(names, Comparator.comparing(String::length));
        System.out.println(Arrays.toString(names));
        for (var name : names) {
            System.out.println(name);
        }
        System.out.println();

        var namesList = Arrays.asList(names);
        System.out.println("namesList.sort() Sorting by length...");
        namesList.sort(Comparator.comparing(String::length));
        System.out.println(namesList);
        namesList.forEach(System.out::println);
    }

    public static void findHer(String[] names) {
        int iteration = 1;
        Predicate<String> firstLetter;
        Predicate<String> endLetter;
        for (var name : names) {
            firstLetter = s -> s.startsWith("Ja");
            endLetter = s -> s.endsWith("r");
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
