import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Set<String> names = Set.of("John", "Jane", "Emily", "Jack");
        System.out.println(names);

        System.out.println("-----------------");

        Set<Integer> ages = new HashSet<>();
        ages.add(20);
        ages.add(30);
        ages.add(40);
        ages.add(50);
        // make ages immutable
        var newUnModifiableSet = Collections.unmodifiableSet(ages);
        System.out.println(newUnModifiableSet);
        newUnModifiableSet.add(60); // throws UnsupportedOperationException
    }
}
