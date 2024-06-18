import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Current working directory (pwd): " + new File("").getAbsolutePath());
        String filename = "files/testing.csv";

        File file = new File(new File("").getAbsoluteFile(),filename);
        System.out.println(file.getAbsoluteFile());
        if(!file.exists()) { // checking if the file exists (LBYL)
            System.out.println("I can't run unless this file exists");
            return;
        }
        System.out.println("I'm good to go");

        for (File f : File.listRoots()) {
            System.out.println(f);
        }

        Path path = Paths.get("files/testing.csv");
        System.out.println(file.getAbsoluteFile());
        if(!Files.exists(path)) { // checking if the file exists (LBYL)
            System.out.println("2. I can't run unless this file exists");
            return;
        }
        System.out.println("2. I'm good to go");
    }

    private static void testFile(String filename) {
        Path path = Paths.get(filename); // we are assuming the file exists (EAFP)
        FileReader reader = null;
        try {
//            List<String> lines = Files.readAllLines(path);
            reader = new FileReader(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // before JDK7, we need to close the reader manually
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Maybe i'll log something along the way");
        }
        System.out.println("File exists and able to use as a resource");
    }

    private static void testFile2(String filename) {
        try (FileReader reader = new FileReader(filename)) {
        } catch (FileNotFoundException e) {
            System.out.println("File: " + filename + " does not exist");
            throw new RuntimeException(e);
        } catch (NullPointerException | IllegalArgumentException badData) { // this or the other
            System.out.println("User has added bad data: " + badData.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Something went wrong");
        } catch (Throwable t) {
            System.out.println("Something really went wrong");
        } finally {
            System.out.println("Maybe i'll log something along the way");
        }
        System.out.println("File exists and able to use as a resource");
    }
}
