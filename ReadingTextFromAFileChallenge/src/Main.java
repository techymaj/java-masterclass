import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        usingAScanner();
    }

    private static void usingAScanner() {
        Path path = Path.of("Love Poem.txt");
        try {
            Scanner scanner = new Scanner(path);
            var result = scanner.tokens()
                    .map(a -> a.replaceAll("\\p{Punct}", ""))
                    .filter(w -> w.length() > 5)
                    .sorted()
                    .map(String::toLowerCase)
                    .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        result.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue,
                        Comparator.reverseOrder()))
                .limit(10)
                .forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}