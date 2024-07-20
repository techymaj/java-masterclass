import java.util.stream.IntStream;

public class ThreadTwo {

    public static void printOdd() {
        System.out.println("Starting " + Thread.currentThread()
                .getName() + "...");
        IntStream.iterate(1, i -> i + 1)
                .filter(i -> i % 2 != 0)
                .mapToObj(i -> "Odd number: " + i)
                .limit(5)
                .forEach(ThreadTwo::oneSecondDelay);
    }

    private static void oneSecondDelay(String odd) {
        try {
            Thread.sleep(1000);
            System.out.println(odd);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
