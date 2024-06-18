import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static final String A_Z_P_PUNCT = "[A-Z].*\\p{Punct}?";

    public static void main(String[] args) {

        String sentence = "I love B.M.W. motorcycles.";
        boolean matched = Pattern.matches(A_Z_P_PUNCT, sentence);
        System.out.println(matched + ": " + sentence);

        Pattern firstPattern = Pattern.compile("[A-Z].*?[.]");
        var matcher = firstPattern.matcher(sentence);
        System.out.println(matcher.matches() + ": " + sentence);
        System.out.println("Sentence.length() = " + sentence.length());
        System.out.println("Matched ending index = " + matcher.end());

        System.out.println(matcher.lookingAt() + ": " + sentence);
        System.out.println("Matched ending index = " + matcher.end());
        System.out.println("Matched on : " + sentence.substring(0, matcher.end()));
//        matcher.reset(); // reset the matcher to the beginning of the input
        System.out.println(matcher.find(0) + ": " + sentence);
        System.out.println("Matched ending index = " + matcher.end());
        System.out.println("Matched on : " + sentence.substring(matcher.start(), matcher.end()));

        System.out.println("Matched on : " + matcher.group());

        String htmlSnippet = """
                <h1>My First Heading</h1>
                <h2>My Second Heading</h2>
                <p>My first paragraph.</p>
                <p>My second paragraph.</p>
                <h3>Summary</h3>
                """;

        Pattern htmlPattern = Pattern.compile("<h(?<level>[1-6])>(?<content>.*?)</h[1-6]>");
        Matcher htmlMatcher = htmlPattern.matcher(htmlSnippet);
        while (htmlMatcher.find()) {
//            System.out.println("group : " + htmlMatcher.group());
//            System.out.println("group0 : " + htmlMatcher.group(0));
            System.out.println(htmlMatcher.group("level") + " " + htmlMatcher.group("content"));
        }

        htmlMatcher.reset();

        htmlMatcher.results().forEach(matchResult -> {
            System.out.println(matchResult.group("level")
                    + " " + matchResult.group("content"));
        });

        String tabbedText = """
                group1  group2  group3
                1   2   3
                a   b   c
                """;

        tabbedText.lines()
                .flatMap(s -> Pattern.compile("\\s+").splitAsStream(s))
                .forEach(System.out::println);

        htmlMatcher.reset();
        String updatedSnippet = htmlMatcher.replaceFirst((mr) ->
                "<em>" + mr.group(2) + "</em>");
        System.out.println("------------------");
        System.out.println(updatedSnippet);
        System.out.println(htmlMatcher.start() + " : " + htmlMatcher.end());
        System.out.println(htmlMatcher.group(2));

        htmlMatcher.usePattern(
                Pattern.compile("<([hH]\\d)>(.*?)</\\1>")
        );
        htmlMatcher.reset();
        //
        System.out.println("Using a back reference: \n" + htmlMatcher
                .replaceFirst("<em>$2</em>") + "\n");

        String replacedHtml = htmlMatcher.replaceAll(
                (mr) -> "<em>" + mr.group(2) + "</em>"
        );
        System.out.println("------------------");
        System.out.println(replacedHtml);

        htmlMatcher.reset();
        StringBuilder sb = new StringBuilder();
        int index = 1;
        while (htmlMatcher.find()) {
            htmlMatcher.appendReplacement(sb,
                    switch (htmlMatcher.group(1).toLowerCase()) {
                        case "h1" -> "<head>$2</head>";
                        case "h2" -> "<em>$2</em>";
                        default -> "<$1>" + index++ + ". $2</$1>";
                    });
        }
        htmlMatcher.appendTail(sb);
        System.out.println(sb);
    }
}
