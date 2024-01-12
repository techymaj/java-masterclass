import java.util.Arrays;
import java.util.List;

public class More {
    public static void main(String[] args) {
        String[] stringArray = new String[] {"My", "name", "is"};
        var stringList = Arrays.asList(stringArray); // -> 2 way bound

        stringArray[2] = "is Slim Shady";
        System.out.println(stringList);

        var list = List.of("sunday", "Monday", "Tuesday");
        list.set(0, "Sun"); // no go. lists are immutable

    }
}
