import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String filename = "testing.csv";
        testFile(filename);

        File file = new File(filename);
        if(!file.exists()) { // checking if the file exists (LBYL)
            System.out.println("I can't run unless this file exists");
            System.out.println("Quitting application");
            return;
        }
        System.out.println("I'm good to go");
    }

    private static void testFile(String filename) {
        Path path = Paths.get(filename); // we are assuming the file exists (EAFP)
        try {
            List<String> lines = Files.readAllLines(path);
        } catch (IOException e) {
            int i = 1/0;
        } finally {
            System.out.println("Maybe i'll log something along the way");
        }
        System.out.println("File exists and able to use as a resource");
    }
}
