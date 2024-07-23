import java.util.List;
import java.util.concurrent.*;

class ColorThreadFactory implements ThreadFactory {

    private String threadName;
    private int colorValue = 1;

    public ColorThreadFactory() {

    }

    public ColorThreadFactory(ThreadColor color) {
        this.threadName = color.name();
    }

    @Override
    public Thread newThread(Runnable r) {
        String name = threadName;
        Thread thread = new Thread(r);
        if (name == null) {
            name = ThreadColor.values()[colorValue].name();
        }
        if (++colorValue > (ThreadColor.values().length - 1)) {
            colorValue = 1;
        }
        thread.setName(name);
        return thread;
    }
}

public class Main {

    public static void notmain() {
        Thread blue = new Thread(Main::countDown, ThreadColor.ANSI_BLUE.name());
        Thread yellow = new Thread(Main::countDown, ThreadColor.ANSI_YELLOW.name());
        Thread red = new Thread(Main::countDown, ThreadColor.ANSI_RED.name());

        blue.start();
        try {
            blue.join();
        } catch (InterruptedException e) {
            System.out.println("Something went wrong");
        }

        yellow.start();
        try {
            yellow.join();
        } catch (InterruptedException e) {
            System.out.println("Something went wrong");
        }

        red.start();
        try {
            red.join();
        } catch (InterruptedException e) {
            System.out.println("Something went wrong");
        }
    }

    public static void singlemain(String[] args) {

//        var blueExecutor = Executors.newSingleThreadExecutor();
//        blueExecutor.execute(Main::countDown);
//        blueExecutor.shutdown();
        try (
                var blueExecutor = Executors.newSingleThreadExecutor(
                new ColorThreadFactory(ThreadColor.ANSI_BLUE));
                var yellowExecutor = Executors.newSingleThreadExecutor(
                        new ColorThreadFactory(ThreadColor.ANSI_YELLOW));
                var redExecutor = Executors.newSingleThreadExecutor(
                        new ColorThreadFactory(ThreadColor.ANSI_RED));
                ) {

            blueExecutor.execute(Main::countDown);
            blueExecutor.shutdown();
            var blueisDone = blueExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);

            if (blueisDone) {
                System.out.println("Blue is done executing");
                redExecutor.execute(Main::countDown);
                redExecutor.shutdown();
                var redisDone = redExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);

                if (redisDone) {
                    System.out.println("Red is done executing");
                    yellowExecutor.execute(Main::countDown);
                    yellowExecutor.shutdown();
                    yellowExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
                }
                System.out.println("Yellow is done executing");
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("All threads finished");
    }

    public static void fixedmain(String[] args) {

        int count = 6;
        try (var multiExecutor = Executors.newFixedThreadPool(3,
                new ColorThreadFactory())) {
            for (int i = 0; i < count; i++) {
                multiExecutor.execute(Main::countDown);
            }
            multiExecutor.shutdown();
        }
    }

    private static void countDown() {
        String threadName = Thread.currentThread().getName();
        var threadColor = ThreadColor.ANSI_RESET;
        try {
            threadColor = ThreadColor.valueOf(threadName.toUpperCase());

        } catch (IllegalArgumentException ignored) {
            // user may pass a bad color name, to which error, just ignore
        }
        String color = threadColor.color();
        for (int i = 20; i >= 0; i--) {
            System.out.println(color + " "
                    + threadName.replace("ANSI_", "")
            + " " + i);
        }
    }

    public static void cachedmain(String[] args) {
        try (var multiExecutor = Executors.newCachedThreadPool()) {
            // number of threads executing even though a thread count wasn't specified will vary
            var redValue = multiExecutor.submit(() -> Main.sum(1, 10, 1, "red"));
            var blueValue = multiExecutor.submit(() -> Main.sum(10, 100, 10, "blue"));
            var greenValue = multiExecutor.submit(() -> Main.sum(2, 20, 2, "green"));
            Thread.sleep(1000);
            System.out.println("-".repeat(100));
            System.out.println(greenValue.get(500, TimeUnit.MILLISECONDS));
            System.out.println(blueValue.get(500, TimeUnit.MILLISECONDS));
            System.out.println(redValue.get(500, TimeUnit.MILLISECONDS));
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        try (var multiExecutor = Executors.newCachedThreadPool()) {
            List<Callable<Integer>> tasks = List.of(
                    () -> Main.sum(1, 10, 1, "red"),
                    () -> Main.sum(11, 100, 10, "cyan"),
                    () -> Main.sum(4, 40, 1, "blue"),
                    () -> Main.sum(1, 20, 10, "green")
            );
            var stateResults = multiExecutor.invokeAny(tasks);
//            stateResults.stream()
//                    .map(Future::state)
//                    .forEach(System.out::println);
            System.out.println(stateResults);
        } catch (InterruptedException ignored) {

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static int sum(int start, int end, int delta, String colorString) {
        var threadColor = ThreadColor.ANSI_RESET;
        try {
            threadColor = ThreadColor.valueOf("ANSI_" + colorString.toUpperCase());
        } catch (IllegalArgumentException ignored) {
        }
        String color = threadColor.color();
        int sum = 0;
        for (int i = start; i <= end; i += delta) {
            sum += i;
        }
        System.out.println(color + Thread.currentThread().getName() + ", " + colorString
                + " " + sum);

        return sum;
    }
}
