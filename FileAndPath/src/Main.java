import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        useFile("testfile.txt");
        usePath("test.txt");
    }

    private static void useFile(String filename) {
        File file = new File(filename);
        boolean fileExists = file.exists();
        System.out.printf("File '%s' %s%n", filename, fileExists ? "exists." : "doesn't exist.");

        if (fileExists) {
            System.out.println("Deleting the file: " + filename);
            fileExists = !file.delete();
        }

        if (!fileExists) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Something went wrong");
            }
            System.out.println("Created file: " + filename);

            if (file.canWrite()) {
                System.out.println("Can write to file here:");
            }
        }
    }

    private static void usePath(String filename) {
        Path path = Paths.get(filename);
        boolean fileExists = Files.exists(path);
        System.out.printf("File '%s' %s%n", filename, fileExists ? "exists." : "doesn't exist.");

        if (fileExists) {
            System.out.println("Deleting the file: " + filename);
            try {
                Files.delete(path);
                fileExists = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!fileExists) {
            try {
                Files.createFile(path);
                System.out.println("Created file: " + filename);

                if (Files.isWritable(path)) {
                    Files.writeString(path, """
                            Here is some data,
                            for my file,
                            just to prove,
                            using the Files class and Path are better!!
                            """);
                }
                System.out.println("And i can read too");
                System.out.println("---------------------");
                Files.readAllLines(path).forEach(System.out::println);
            } catch (IOException e) {
                System.out.println("Something went wrong");
            }
        }
    }
}
