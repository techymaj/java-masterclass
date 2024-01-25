import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MiniChallenges {

    public static void main(String[] args) {

        System.out.println("----------Challenge 1------------");
        Consumer<String> printTheParts = (String sentence) -> {
            String[] parts = sentence.split(" ");
            Arrays.asList(parts).forEach(part -> {
                var append = part + ".";
                System.out.println(append);
            });
        };
        // accept() method is declared in Consumer interface
        printTheParts.accept("We are learning about lambdas");
        System.out.println();

        System.out.println("----------Challenge 2------------");
        UnaryOperator<String> challenge2 = source -> everySecondChar(source);
        String result = challenge2.apply("Every second counts");
        System.out.println(result);
        Function<String, String> c2 = source -> everySecondChar(source);
        String result2 = c2.apply("Every second counts");
        System.out.println(result2);

        System.out.println();
        System.out.println("----------Challenge 3-------------");
        Function<String, String> c3 = digits -> {

            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < digits.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(digits.charAt(i));
                }
            }

            return returnVal.toString();
        };
        String result3 = c3.apply("1234567890");
        System.out.println(result3);

        System.out.println();
        System.out.println("----------Challenge 4------------");

        String mini4 = everySecondChar(s -> everySecondChar(s),"1234567890");
        System.out.println(mini4);

        System.out.println();
        System.out.println("----------Challenge 5------------");
        String mini5 = everySecondChar(c3, "1234567890");
        System.out.println(mini5);

        System.out.println();
        System.out.println("----------Challenge 6------------");
        // gottaLoveLambdas returns an Instance of tye String as specified in Supplier<String>
        Supplier<String> gottaLoveLambdas = () -> "I love Java";
        // gottaLoveLambdas is a deferred snippet of code awaiting execution
        String whatDoYouLove = gottaLoveLambdas.get();
        System.out.println(whatDoYouLove);
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

    public static String everySecondChar(Function<String, String> lambda, String source) {

        return lambda.apply(source);
    }
}
