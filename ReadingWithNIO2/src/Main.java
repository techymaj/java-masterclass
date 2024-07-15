import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static Pattern pattern = Pattern.compile("(.{15})(.{3})(.{12})(.{8})(.{2}).*");
    public static void main(String[] args) {
        usingReadAllLines();
    }

    static void base() {
        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset());

        Path path = Path.of("fixedWidth.txt");

        try {
            System.out.println(new String(Files.readAllBytes(path)));
            System.out.println("-------------------------");
            // to be used if you know you're reading a text file
            // to handle security checks and access issues
            System.out.println(Files.readString(path));
        } catch (IOException ignored) {

        }
    }

    static void usingReadAllLines() {
        Path path = Path.of("fixedWidth.txt");
        Set<String> values = new TreeSet<>();
        try {
            Files.readAllLines(path).forEach(
                    s -> {
                        if (!s.startsWith("Name")) {
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.matches()) {
                                values.add(matcher.group(3).trim());
                            }
                        }
                    }
            );
            System.out.println(values);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}