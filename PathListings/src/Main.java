import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        Path path = Path.of("files/testing.txt");
        printPathInfo(path);
    }

    private static void printPathInfo(Path path) {
        System.out.println("Path: " + path);
        System.out.println("fileName: " + path.getFileName());
        System.out.println("parent: " + path.getParent());

        Path absolutePath = path.toAbsolutePath();
        System.out.println("Absolute path: " + absolutePath);
        System.out.println("Absolute path root: " + absolutePath.getRoot());
        System.out.println("Root: " + path.getRoot());
        System.out.println("isAbsolute: " + path.isAbsolute());

        System.out.println(absolutePath.getRoot());
//        int i = 1;
//        var it = path.toAbsolutePath().iterator();
//        while (it.hasNext()) {
//            System.out.println(".".repeat(i++) + " " + it.next());
//        }
        // getName gives you more flexibility in how
        // you might traverse through the file tree
        int pathParts = absolutePath.getNameCount();
        for (int i = 0; i < pathParts; i++) {
            // i  + 1 because the first folder isn't the root
            System.out.println(".".repeat(i + 1) + " " + absolutePath.getName(i));
        }
        System.out.println("----------------------------------");
    }

    private static void logStatement() {

    }
}
