package collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

public class CollectionsDemo {

    public static void show() {
        Collection<String> collection = new ArrayList<>();
        collection.add("I");
        collection.add("Am");
        collection.add("Number");
        collection.add("Four");
        Collections.addAll(collection, "5", "4", "3", "2", "1");

        Collection<String> other = new ArrayList<>(collection);

        System.out.println(other == collection); // false
        System.out.println(other.equals(collection)); // true
        collection.remove("Am");

        var stringArr = other.toArray(new String[0]);
        Stream.of(stringArr)
                .filter(CollectionsDemo::isNumber)
                .forEach(System.out::println);

        System.out.println("========== Iterator ==========");
        var iterator = collection.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    static boolean isNumber(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
