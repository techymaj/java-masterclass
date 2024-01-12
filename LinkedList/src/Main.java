import java.util.LinkedList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        var queue = new LinkedList<String>();
        queue.add("kampala");
        queue.add("Wakiso");
        queue.add("Mbarara");
        queue.add("Mbale");
        queue.add("Gulu");

        var listIterator = queue.iterator(); // the call to next() will return the element at this index

//        String previousTown = listIterator.next(); // Wakiso
//        System.out.println("Trip starts at " + previousTown);
        while (listIterator.hasNext()) {
//            var town = listIterator.next();
            if (listIterator.next().equals("kampala")) listIterator.remove();
//            System.out.println("---> From " + previousTown + " to " + town);
//            previousTown = town;
        }
        System.out.println(queue);
    }
}
