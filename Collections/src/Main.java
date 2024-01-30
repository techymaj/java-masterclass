import java.util.*;

public class Main {

    public static void main(String[] args) {

        String[] names = {"Tom", "Sam", "Bob", "Alice"};
        Collection<String> list = new HashSet<>(List.of(names));

        var isAdded = list.add("Bill"); // (1)
        list.addAll(Arrays.asList("Kate", "Sam", "Tom"));
        System.out.println(isAdded);

        System.out.println(list);
        System.out.println("Is Gary in the list? " + (list.contains("Gary") ? "Yes" : "No"));

        list.removeIf(x -> x.startsWith("B")); // (2)
        System.out.println(list);

//        list.sort(); // The Collection Interface does not have a sort method but the List Interface does
        // because the Collection Interface does not define the order of elements in the collection
        System.out.println("Collections.sort()");
        List<String> list2 = new ArrayList<>(list);
        Collections.sort(list2); // (3) // takes a List as an argument hence the conversion above
        System.out.println(list2);

        // Collection is the interface
        // Collections is the class tha

        // all methods defined on the Collection Interface but executed
        // on a specific implementation of the Collection Interface

        takeMeToTheMovies("The Matrix", "The Matrix Reloaded", "The Matrix Revolutions");
    }

    public static void takeMeToTheMovies(String... movieName) {
        System.out.print("I want to see the following movies: ");
        for (var movie : movieName) {
            System.out.printf("%s, ", movie);
        }
    }
}
