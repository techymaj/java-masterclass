import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringJoiner;

public class Person {

    public static void main(String[] args) {

        Path path = Path.of("people.json");
        try {
            Files.writeString(path, toJSON().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static StringJoiner toJSON() {

        return new StringJoiner(",", "{", "}")
                .add("\"name\": " + "\"John\"")
                .add("\"age\": " + 20);
    }
}
