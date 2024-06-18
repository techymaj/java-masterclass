import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static String regex = "([\\w.-]+)@(\\p{Alnum}.*)[.](\\p{Alnum}.*)";

    public static void main(String[] args) {

        List<String> emails = List.of(
                "wilfriedmajaliwa@.com",
            "wilfriedmajaliwa@gmail.com",
            "wilfried.majaliwa@gmail.com",
                "wilfried!-majaliwa@gmail.com",
                "wilfried_majaliwa@gmail.com",
                "wilfried.majaliwa@gmail.co.ug",
                "wilfried.majaliwa@gmail",
            "wil123maj@gmail.com",
            ".majaliwa@gmail.com",
            "@gmail.com"
        );

        Pattern pattern = Pattern.compile(regex);

        for (var email : emails) {
            Matcher matcher = pattern.matcher(email);
            System.out.println(email + " is: " + matcher.matches());
        }
    }
}
