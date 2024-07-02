import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Collector;

public class Main {
    public static void main(String[] args) {
        // we are not opening this file here
        // but rather, working with a file handler
        File file = new File("test.txt");
        System.out.println(file.exists() ? "Exists." : "Doesn't exist.");

        try (
                FileWriter fileWriter = new FileWriter(file, true);
                FileReader fileReader = new FileReader(file);
                Scanner scanner = new Scanner(System.in)
        ) {
            fileWriter.write("Holla");
            char[] chars = new char[(int) file.length()];
            fileReader.read(chars);
            System.out.println(new String(chars));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
