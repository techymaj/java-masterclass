import java.time.Period;
import java.util.stream.IntStream;

public class Stats {

    public static void main(String[] args) {

        var print  = IntStream
                .iterate(0, i -> i <= 1000, i -> i = i + 3)
                .summaryStatistics();

        System.out.println(print);
        String name = "Susan";
        name.toUpperCase();

        Period.between()

    }
}
