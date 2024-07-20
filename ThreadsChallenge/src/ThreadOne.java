import java.util.stream.IntStream;

public class ThreadOne extends Thread {

    @Override
    public void run() {
        System.out.println("Starting " + Thread.currentThread()
                .getName() + "...");
        IntStream.iterate(1, i -> i + 1)
                .filter(i -> i % 2 == 0)
                .boxed()
                .map(i -> "Even number: " + i)
                .limit(5)
                .forEach(ThreadOne::oneSecondDelay);
    }

    private static void oneSecondDelay(String even) {
        try {
            Thread.sleep(500);
            System.out.println(even);
        } catch (InterruptedException e) {
            ThreadOne.currentThread().interrupt();
            System.out.println("Can't print even numbers anymore");
        }
    }
}
