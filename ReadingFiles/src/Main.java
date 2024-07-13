import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
//        usingPathAndFiles();
//        usingFileReader();
        usingBufferedReader();
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
            // creating the ability to read more than one char at a time
            char[] block = new char[1_000];
            while ((data = fileReader.read(block)) != -1) {
                String content = new String(block, 0, data);
                System.out.printf("---> [%d chars] %s%n", data, content);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void usingBufferedReader() {
        try (
                FileReader fileReader = new FileReader("file.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader)
                ) {
            var block = bufferedReader.lines(); // can also use readLine
            block.forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
