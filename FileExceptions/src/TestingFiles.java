import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestingFiles {

    public static void main(String[] args) {
        Path filename = Path.of("files/testing.txt");
        try {
            String myFile = Files.readString(filename);
            System.out.println(myFile);
            int sum = 0;
            char[] arr = myFile.toCharArray();
            for (var c : arr)  {
                if (Character.isDigit(c)) {
                    sum += Character.getNumericValue(c);
                }
            }
            System.out.println("Total: " + sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
