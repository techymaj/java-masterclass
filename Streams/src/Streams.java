import java.util.ArrayList;
import java.util.List;

public class Streams {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Jennifer");
        names.add("Queen");

        var count = names.stream()
                .count();
        System.out.println(count);
    }

    static void m() {
        System.out.println("m");
    }
}
