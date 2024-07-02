import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    record Person(String name, int age) {
        @Override
        public String toString() {
            return name + " is " + age;
        }
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>(Arrays.asList(
                new Person("Jax", 29),
                new Person("Maria", 30),
                new Person("Brian", 29)
        ));

        var compareName = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.name.compareTo(o2.name);
            }
        };

        people.sort((o1, o2) -> o1.name.compareTo(o2.name));
        System.out.println(people);
    }
}
