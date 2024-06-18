import java.util.Arrays;
import java.util.Scanner;

public class PartsBoundaryMatchers {

    public static void main(String[] args) {

        String testString = "Anyone can Learn abc's, 123's, and any regular expression";
        String replacement = "(-)";
        String[] patterns = {
                "[a-zA-Z]*$", // match the last word
                "^[a-zA-Z]{3}", // match the first letter regardless of case
                "[aA]ny\\b" // match the word "any" with a word boundary
        };

        for (String pattern : patterns) {
            String output = testString.replaceFirst(pattern, replacement);
//            System.out.println("Pattern: " + pattern + " => " + output);
        }

        // Song of the Witches in Macbeth, a play by William Shakespeare
        String paragraph = """
                Double, double toil and trouble;
                Fire burn and caldron bubble.
                Fillet of a fenny snake,
                In the caldron boil and bake;
                Eye of newt and toe of frog,
                Wool of bat and tongue of dog,
                Adder's fork and blind-worm's sting,
                Lizard's leg and owlet's wing,
                For a charm of powerful trouble,
                Like a hell-broth boil and bubble.
                """;
        // \\R is a linebreak boundary matcher and matches any Unicode line break sequence
        // \\s+ is a whitespace boundary matcher and matches any whitespace character
        String[] lines = paragraph.split("\\R");
        System.out.println("This paragraph has " + lines.length + " lines.");
        String[] words = paragraph.split("\\s+");
        System.out.println("This paragraph has " + words.length + " words.");
        System.out.println(paragraph.replaceAll("[a-zA-z]+ble", "[GRUB]"));

        Scanner scanner = new Scanner(paragraph);
        System.out.println(scanner.delimiter());
        scanner.useDelimiter("\\R");
        System.out.println(scanner.delimiter());
//        scanner.tokens()
//                // \\p{Punct} is a punctuation boundary matcher and matches any punctuation character
//                .map(word -> word.replaceAll("\\p{Punct}", ""))
//                .flatMap(line -> Arrays.stream(line.split("\\s+")))
//                .filter(word -> word.matches("[a-zA-Z]+ble"))
//                .forEach(System.out::println);
        System.out.println(scanner.findInLine("\\p{Alnum}+ble\\p{Punct}?"));
        System.out.println(scanner.findInLine("\\p{Alnum}+ble\\p{Punct}?"));
        System.out.println(scanner.findInLine("\\p{Alnum}+ble\\p{Punct}?"));
        System.out.println(scanner.findInLine("[a-zA-Z]+ble\\p{Punct}?")); // reached end of line
//        while (scanner.hasNext()) {
//            String element = scanner.next();
//            System.out.println(element);
//        }
        scanner.close();
    }
}
