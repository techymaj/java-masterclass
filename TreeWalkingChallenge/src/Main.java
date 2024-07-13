import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Main {
    static int files = 0;
    static int folders = 0;
    static long fileSize = 0;

    public static void main(String[] args) {
        Path path = Path.of("/users/mbuto/documents");
        try (
                Stream<Path> pathStream = Files.walk(path, Integer.MAX_VALUE)
        ) {
            pathStream
                    .forEach(Main::summary);
            System.out.println(files + " files " + folders + " folders" + " " + "["+fileSize+"]bytes");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static long summary(Path path) {

        if (Files.isRegularFile(path)) {
            files++;
            try {
                fileSize += Files.size(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            folders++;
            try {
                fileSize += Files.size(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return fileSize;
    }
}
