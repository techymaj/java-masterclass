public class Parts {

    public static void main(String[] args) {

        String testString = "Anyone can Learn abc's, 123's, and any regular expression";
        String replacement = "(-)";
        String[] patterns = {
                "[a-zA-Z]*", // regardless of case
                "[0-9]{2}",
                "[A-Z]*"
        };

        for (String pattern : patterns) {
            String output = testString.replaceFirst(pattern, replacement);
            System.out.println("Pattern: " + pattern + " => " + output);
        }
    }
}
