import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.UnaryOperator;

public class Challenge {
    static Random random = new Random(26);

    public static void main(String[] args) {
        String[] mixedNames = new String[]{
                "MariA",
                "GRace",
                "Jax",
                "Anna"
        };

        List<UnaryOperator<String>> operations = List.of(
                String::toUpperCase,
                Challenge::insertMiddleName,
                Challenge::reverseFirstName,
                Challenge::spacer
        );

        hereIAm(mixedNames, operations);
    }

    private static void hereIAm(String[] nameArr, List<UnaryOperator<String>> fnList) {
        Arrays.asList(nameArr).forEach(
                function -> fnList.forEach(
                        operation -> {
                            var result = operation.apply(function);
                            System.out.println(result);
                        }
                )
        );
    }

    private static String insertMiddleName(String s) {
        return s + " " + (char) random.nextInt(65, 91) + ".";
    }

    private static String reverseFirstName(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    private static String spacer(String s) {
        return "-".repeat(50);
    }
}
