import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class Main {

    public static void main(String[] args) {

//        usingBareBonesScanner();
        usingTokens();
    }

    private static void usingBareBonesScanner() {
        try (
                Scanner scanner = new Scanner(new File("file.txt"))
        ) {
            // scanner.hasNextLine ---> is there any more data to process
            while(scanner.hasNextLine()) {
                // nextLine gets the next line in the file
                System.out.println(scanner.nextLine());
            }
        } catch (IOException ignored) {

        }
    }

    static void usingTokens() {
        try (
                Scanner scanner = new Scanner(new BufferedReader(new FileReader("fixedWidth.txt")))
                ) {
//            scanner.useDelimiter("$"); // end of line
//            scanner.tokens().forEach(System.out::println);
//            scanner.findAll("[A-Za-z]{10,}")
//                    .map(MatchResult::group)
//                    .distinct()
//                    .sorted()
//                    .forEach(System.out::println);
            var results = scanner.findAll(
                    "(.{15})(.{3})(.{12})(.{8})(.{2}).*")
                    .map(
                            matchResult -> matchResult.group(1)
                                    .trim()
                    )
                    .skip(1)
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);
            System.out.println(Arrays.toString(results));

        } catch (IOException ignored) {}
    }
}