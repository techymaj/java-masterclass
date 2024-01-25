import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;

public class Main {
    record Person(String firstName, String lastName) {
        @Override
        public String toString() {
            return firstName + " " + lastName;
        }
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>(Arrays.asList(
                new Main.Person("John", "Doe"),
                new Person("Hank", "Pim"),
                new Person("Tony", "Stark"),
                new Person("Pepper", "Stark"),
                new Person("Jane", "Foster"))
        );

        people.forEach(s -> System.out.println(s));

//        var customComparator = new Comparator<Person>() {
//            @Override
//            public int compare(Person p1, Person p2) {
//                return p1.lastName.compareTo(p2.lastName);
//            }
//        };

        // Sort by first name
        people.sort((p1, p2) -> p1.lastName.compareTo(p2.lastName));

//        System.out.println(people);

//        @FunctionalInterface
        interface EnhancedComparator<T> extends Comparator<T> {
            int secondLevelCompare(T t1, T t2);
        }

        var comparatorMixed = new EnhancedComparator<Person>() {

            @Override
            public int compare(Person o1, Person o2) {
                int result = o1.lastName.compareTo(o2.lastName);
                // if last names are equal, use second level comparator
                return result == 0 ? secondLevelCompare(o1, o2) : result;
            }

            @Override
            public int secondLevelCompare(Person t1, Person t2) {
                return t1.firstName.compareTo(t2.firstName);
            }
        };

        people.sort(comparatorMixed);
        System.out.println(people);

        int result = calculator(5, 10, (a, b) -> a + b);
        System.out.println(result);

        var coords = Arrays.asList(
                new double[]{47.6062, -122.3321},
                new double[]{40.7128, -74.0060},
                new double[]{51.5074, -0.1278});
        coords.forEach(coord -> System.out.println(Arrays.toString(coord)));

        BiConsumer<Double, Double> p1 = (lat, lng) -> System.out.println("Seattle: " + lat + ", " + lng);

        var firstPoint = coords.get(0);
        processPoint(firstPoint[0], firstPoint[1], p1);

        System.out.println("-------------");

        coords.forEach(coord -> processPoint(coord[0], coord[1], p1));
        coords.forEach(coord -> processPoint(coord[0], coord[1],
                        (lat, lng) -> System.out.printf("[Lat: %f]: [Long: %f]\n", lat, lng)
                )
        );

    }

    public static <T> T calculator(T t1, T t2, BinaryOperator<T> operation) {
        T result = operation.apply(t1, t2);
        System.out.println("Result of operation is: " + result);
        return result;
    }

    public static <T> void processPoint(T t1, T t2, BiConsumer<T, T> consumer) {
        consumer.accept(t1, t2);
    }
}
