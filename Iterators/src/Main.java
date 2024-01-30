import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>(List.of("a", "b", "c"));

        ListIterator<String> iterator = list.listIterator();
        if (iterator.hasNext()) {
            // iterator.next() returns the next element and advances the cursor position.
            System.out.println(iterator.next());
            iterator.remove();
        }
        System.out.println(list);
    }
}
