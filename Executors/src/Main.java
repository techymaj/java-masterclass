import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

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

    public static void main(String[] args) {

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
}
