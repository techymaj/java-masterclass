import java.util.function.Consumer;

public class MiniChallenge1 {

    public static void main(String[] args) {

        Consumer<String> printTheParts = (String sentence) -> {
            String[] parts = sentence.split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };
        // accept() method is implemented in Consumer interface
        printTheParts.accept("We are learning about lambdas");
    }
}
