import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Main {
    public static void main(String[] args) {

        Path path = Path.of("public/assets/icons");
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (var dirs = Files
                .find(path,
                        Integer.MAX_VALUE,
                        (p, attr) -> true)) {
                dirs
                        .map(Main::createIndexFile)
                        .map(Main::writeToIndex)
                        .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static Path createIndexFile(Path dir) {
        try {
            return Files.createFile(Path.of(dir + "/index.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static Path writeToIndex(Path dir) {
        try {
            return Files
                    .writeString(Path.of(dir + "/index.txt"),
                            dir + "/index.txt",
                            StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}