import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int numbersLength = 100_000;
        long[] numbers = new Random().longs(
                numbersLength,
                1,
                numbersLength
        ).toArray();

        long start = System.nanoTime();
        double averageSerial = Arrays.stream(numbers)
                .average()
                .orElseThrow();
        long elapsedSerial = System.nanoTime() - start;
        System.out.printf("Ave = %.2f , elapsed = %d nanos or %.2f ms%n",
                averageSerial, elapsedSerial, elapsedSerial / 1_000_000.0);

        start = System.nanoTime();
        double averageParallel = Arrays.stream(numbers)
                .parallel()
                .average()
                .orElseThrow();
        long elapsedParallel = System.nanoTime() - start;
        System.out.printf("Ave = %.2f , elapsed = %d nanos or %.2f ms%n",
                averageParallel, elapsedParallel, elapsedParallel / 1_000_000.0);

        start = System.nanoTime();
        double averageParallelTwo = Arrays.stream(numbers)
                .parallel()
                .average()
                .orElseThrow();
        long elapsedParallelTwo = System.nanoTime() - start;
        System.out.printf("Ave = %.2f , elapsed = %d nanos or %.2f ms%n",
                averageParallelTwo, elapsedParallelTwo, elapsedParallelTwo / 1_000_000.0);
    }
}
