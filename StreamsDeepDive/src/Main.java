import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main <T> {
    T age;
    public static void main(String[] args) {
        IntStream.iterate(1, i -> i + 1)
                .map(x -> x * 2)
                .limit(10);
//                .forEach(System.out::print);
        System.out.println();

        Random random = new Random();
        Stream.generate(() -> random.nextInt(2)) // between 0 and 2 (exclusive)
                .limit(8);
//                .forEach(System.out::print);

        IntStream.rangeClosed(1, 10)
                .takeWhile(x -> x < 5);
//                .forEach(System.out::println);

        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    static <U> void iam(U name) {
//            U age = s;
    }


}