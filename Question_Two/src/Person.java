import java.util.Arrays;
import java.util.Scanner;

public class Person {

    String name;

    public static void main(String[] args) {

        Person[] person = new Person[3]; // [Obj1, Obj2, Obj3]
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < person.length; i++) {
            person[i] = new Person();
            System.out.println("Enter a name:");
            person[i].name = scanner.nextLine();
        }

        System.out.println(Arrays.toString(person));
    }

    @Override
    public String toString() {
        return name;
    }
}
