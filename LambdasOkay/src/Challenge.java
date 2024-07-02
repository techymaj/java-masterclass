import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Challenge {

    public static void main(String[] args) {

        String sentence = "I am";

        Consumer<String> printTheParts = s -> {

            String[] parts = s.split(" ");
            Arrays.asList(parts).forEach(System.out::println);
        };
        printTheParts.accept(sentence);
        var s = lambda.apply("1234567890");
        System.out.println(s);
        System.out.println("------------------------");
        everySecondCharacter(lambda, "1234567890");
        System.out.println(iLoveJava.get());
    }

    public static String everySecondChar(String source) {

        StringBuilder returnVal = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            if (i % 2 == 1) {
                returnVal.append(source.charAt(i));
            }
        }
        return returnVal.toString();
    }

    static UnaryOperator<String> lambda = source -> {
        StringBuilder returnVal = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            if (i % 2 == 1) {
                returnVal.append(source.charAt(i));
            }
        }
        return returnVal.toString();
    };

    public static <T> void everySecondCharacter(Function<T, T> fn, T value) {
        T result = fn.apply(value);
        System.out.println(result);
    }

    static Supplier<String> iLoveJava = () -> "I Love Java";
}
