import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        var champions = unionSet();
        System.out.println("--- Union Set ---");
        System.out.println(champions);
        System.out.println();

        var intersection = intersectionSet();
        System.out.println("--- Intersection Set ---");
        System.out.println(intersection);
        System.out.println();

        var difference = difference();
        System.out.println("--- Difference Set ---");
        System.out.println(difference);
    }

    private static Set<String> unionSet() {
        Set<String> epl = new HashSet<>();
        epl.add("Arsenal");
        epl.add("Chelsea");
        epl.add("Manchester United");
        epl.add("Manchester City");
        epl.add("Liverpool");
        epl.add("Tottenham Hotspur");

        Set<String> ucl = new HashSet<>();
        ucl.add("Real Madrid");
        ucl.add("Barcelona");
        ucl.add("Arsenal");
        ucl.add("Manchester City");

        Set<String> champions = new HashSet<>(epl);
        // using addAll() method to form a Union set
        champions.addAll(epl);
        champions.addAll(ucl);
        return champions;
    }

    private static Set<String> intersectionSet() {
        Set<String> epl = new HashSet<>();
        epl.add("Arsenal");
        epl.add("Chelsea");
        epl.add("Manchester United");
        epl.add("Manchester City");
        epl.add("Liverpool");
        epl.add("Tottenham Hotspur");

        Set<String> ucl = new HashSet<>();
        ucl.add("Real Madrid");
        ucl.add("Barcelona");
        ucl.add("Arsenal");
        ucl.add("Manchester City");

        // using retainAll() method to form an intersection set
        // in the epl set, retain only elements present in both the epl and ucl set
        epl.retainAll(ucl);
        return epl;
    }

    private static Set<String> difference() {
        Set<String> epl = new HashSet<>();
        epl.add("Arsenal");
        epl.add("Chelsea");
        epl.add("Manchester United");
        epl.add("Manchester City");
        epl.add("Liverpool");
        epl.add("Tottenham Hotspur");

        Set<String> ucl = new HashSet<>();
        ucl.add("Real Madrid");
        ucl.add("Barcelona");
        ucl.add("Arsenal");
        ucl.add("Manchester City");

        // using removeAll() method to form a difference set
        // in the epl set, remove all elements present in the ucl set
        epl.removeAll(ucl);
        return epl;
    }
}
