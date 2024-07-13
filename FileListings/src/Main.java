import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Path path = Path.of("");
        System.out.println("pwd: " + path.toAbsolutePath());

        try (
                Stream<Path> paths = Files.list(path);
                Stream<Path> tree = Files.walk(path, 1);
                Stream<Path> findPaths = Files.find(path, 2,
                        (p, _) -> Files.isRegularFile(p))
        ) {
            paths
                    .map(Main::listDir)
                    .forEach(System.out::println);
            System.out.println("-".repeat(50));
            tree
                    .forEach(System.out::println);
            System.out.println("-".repeat(50));
            findPaths
                    .map(Main::listDir)
                    .forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        path = path.resolve(".idea");
        System.out.println("==================Directory Stream================");
        try (var dirs = Files.newDirectoryStream(path, "*.xml")) {
            dirs.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String listDir(Path path) {
        var isFile = Files.isRegularFile(path);
        return isFile ? path + " is a file" : path + " is a directory";
    }

//    private static String walkTree(Path path) {
//
//    }
}
