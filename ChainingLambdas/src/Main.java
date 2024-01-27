import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {

        String name = "Wilfried";
        Function<String, String> uName = String::toUpperCase;
        System.out.println(uName.apply(name));

        Function<String, String> lName = s -> s.concat(" Majaliwa");
        System.out.println(lName.apply(name));

        // execute uName and then lName using name as input
        var uNameThenLName = uName.andThen(lName).apply(name);
        // uName upper cases the name and then lName appends the last name
        System.out.println(uNameThenLName);

        // execute lName lambda first and then uName
        var composed = uName.compose(lName).apply(name);
        System.out.println(composed);

        Function<String, String[]> f0 = uName
                .andThen(s -> s.concat(" Majaliwa"))
                .andThen(s -> s.split(" "));
        System.out.println(Arrays.toString(f0.apply(name)));

        // chained lambdas don't have to return the same type
        Function<String, String> f1 = uName
                .andThen(s -> s.concat(" Majaliwa"))
                .andThen(s -> s.split(" "))
                .andThen(s -> s[1].toUpperCase() + ", " + s[0]);
        System.out.println(f1.apply(name));

        Function<String, Integer> f2 = uName
                .andThen(s -> s.concat(" Majaliwa"))
                .andThen(s -> s.split(" "))
                .andThen(s -> s[1].toUpperCase() + ", " + s[0])
                .andThen(String::length)
                .andThen(i -> i * 2);
        System.out.println(f2.apply(name));

        String[] names = {"Wilfried", "Majaliwa", "Ann", "Bob"};
        Consumer<String> print = s -> System.out.print(s.charAt(0));
        Consumer<String> printIt = System.out::println;
        Arrays.asList(names).forEach(print
                .andThen(s -> System.out.print(" - "))
                .andThen(printIt));

        // Convenience methods for Predicate
        Predicate<String> p1 = s -> s.equals("Wilfried");
        Predicate<String> p2 = s -> s.equalsIgnoreCase("WILFried");
        Predicate<String> p3 = s -> s.startsWith("Wil");
        Predicate<String> p4 = s -> s.endsWith("fred");

        Predicate<String> combined = p1.or(p2);
        System.out.println(combined.test(name));

        Predicate<String> combined2 = p3.and(p4);
        System.out.println(combined2.test(name));

        Predicate<String> combined3 = p3.and(p4).negate();
        System.out.println(combined3.test(name));

        record Person(String firstName, String lastName) {
        }

        List<Person> list = new ArrayList<>(List.of(
                new Person("Wilfried", "Majaliwa"),
                new Person("Ann", "Karenina"),
                new Person("Ann", "Anna"),
                new Person("Bob", "Moose")
        ));

        list.sort((o1, o2) -> o1.firstName().compareTo(o2.firstName()));
        list.forEach(System.out::println);

        System.out.println("----");

        list.sort(Comparator.comparing(Person::firstName));
        list.forEach(System.out::println);

        System.out.println("----");

        list.sort(Comparator.comparing(Person::firstName)
                .thenComparing(Person::lastName)
        );
        list.forEach(System.out::println);

        System.out.println("----");

        list.sort(Comparator.comparing(Person::firstName)
                .thenComparing(Person::lastName)
                .reversed()
        );
        list.forEach(System.out::println);
    }
}
