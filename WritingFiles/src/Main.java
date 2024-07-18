import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("hello.txt");
            try (BufferedWriter bufferedWriter = Files.
                    newBufferedWriter(path, StandardOpenOption.APPEND )) {
                bufferedWriter.write("Hello there!");
            } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        try {
//            Files.writeString(path, "Hola!");
//            IntStream.rangeClosed(1, 10)
//                    .mapToObj(x -> {
//                        try {
//                            return Files.writeString(path, ("(" + x + ")"), StandardOpenOption.APPEND);
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    })
//                    .peek(p -> System.out.println("You're now writing " + p))
//                    .forEach(System.out::println);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        }
    }
}