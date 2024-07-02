import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class Main {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>(List.of(
                "Eric",
                "Sam",
                "Frank"
        ));

        names.forEach(System.out::println);

        BinaryOperator<String> operator = String::concat;
        String result = operator.apply("I ", "am");
        System.out.println(result);

        Name myName = Main::fullName; // Unbounded M.R
        String s = myName.whoami("Majaliwa", "Mbuto", "Wilfried");
        System.out.println(s);
    }

    private static String fullName(String lname, String middleName, String fname) {
        return lname + " " + middleName + " " + fname;
    }
}

@FunctionalInterface
interface Name {
    String whoami(String s1, String s2, String s3);
}
