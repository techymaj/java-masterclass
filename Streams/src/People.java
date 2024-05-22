import java.util.List;

public class People {

    record Person(String name, String gender) {}

    public static void main(String[] args) {

        List<Person> people = List.of(
                new Person("John", "M"),
                new Person("Jennifer", "F"),
                new Person("Queen", "F"),
                new Person("King", "M")
        );

        people.stream()
                .filter(person -> person.gender.equals("M"))
                .forEach(System.out::println);
    }
}
