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
        epl.add("Arsenal");
        epl.add("Manchester City");

        Set<String> champions = new HashSet<>(epl);
        // using addAll() method to form a Union set
        champions.addAll(epl);
        champions.addAll(ucl);

        System.out.println(champions);
    }
}
