import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
//        usingPathAndFiles();
        usingFileReader();
    }

    static void usingPathAndFiles() {
        Path path = Path.of("file.txt");
        try {
            var list = Files.readAllLines(path);
            list.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void usingFileReader() {
        try (
                FileReader fileReader = new FileReader("file.txt");
                ) {
            int data;
            while ((data = fileReader.read()) != -1) {
                System.out.println((char ) data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
