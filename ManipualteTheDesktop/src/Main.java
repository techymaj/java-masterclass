import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("../../Documents/dev/Java_NIO2");
//            try (
//                    var x = Files.list(path)
//                    ) {
//                x.forEach(System.out::println);
//            } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}