package lists;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {

    public static void show() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        var removed = list.remove("b");
        if (removed) System.out.println("El removed successfully");
        list.add(1, "4");
        System.out.println(list);
    }
 }
