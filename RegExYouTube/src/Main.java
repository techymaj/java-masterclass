import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        String regex = "\\w+\\s+\\S+\\s+\\w+\\W?$"; // ^ means not, so [^abc] means not a, b, or c
        String input = "Thanks for Watching!";

        System.out.println("Result: " + Pattern.compile(regex).matcher(input).find());
    }
}
