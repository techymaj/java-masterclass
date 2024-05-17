import java.util.Arrays;

record Person(String name, String dob, Person[] kids) {
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", kids=" + Arrays.toString(kids) +
                '}';
    }
}

public class Main {

    public static void main(String[] args) {

        Person joe = new Person("Joe", "01/01/1990", new Person[]{new Person("Kid1", "01/01/2010", null)});
        Person jane = new Person("Jane", "11/01/1990", null);
        Person john = new Person("John", "21/01/1990", null);
        Person jill = new Person("Jill", "04/01/1990", new Person[]{joe, jane});
        Person jack = new Person("Jack", "02/01/1990", null);

        Person[] persons = {joe, jane, john, jill, jack};
        Person[] copiedPersons = Arrays.copyOf(persons, persons.length); // shallow copy

        // since there's no way to mutate the record, a deep copy would be unnecessary

        var jillsKids = copiedPersons[3].kids();
        jillsKids[0] = jane;

        for (int i = 0; i < persons.length; i++) {
            if (persons[i] == copiedPersons[i]) {
                System.out.println("Equal references " + persons[i]);
            }
        }
    }
}
