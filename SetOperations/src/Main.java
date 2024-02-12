import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

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
//        champions.addAll(epl);
//        champions.addAll(ucl);

//        System.out.println(champions);

        System.out.println("-----------------");
        // retainAll() method to form an intersection set
        // in the epl set, retain only elements present in both the epl and ucl set
        epl.retainAll(ucl);

        System.out.println(epl);
        System.out.println(ucl);

        System.out.println("-----------------");
        // removeAll() method to form a difference set
        // in the ucl set, remove everything that is in the epl set
        ucl.removeAll(epl);

        System.out.println(epl);
        System.out.println(ucl);
    }
}
