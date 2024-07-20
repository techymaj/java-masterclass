public class Main {

    public static void main(String[] args) {

        System.out.println("Main thread running...");
        try {
            System.out.println("Main thread sleeping for 1 second...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(() -> {
            var name = Thread.currentThread().getName();
            System.out.println(name + " should take 10 dots to run");
            for (int i = 0; i < 10; i++) {
                System.out.print(".");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("\nWhoops!! " + name + " interrupted");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println("\n" + name + " finished running");
        });

        Thread installThread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.print("--->");
                try {
                    Thread.sleep(250);
                    System.out.println("Installing step " + (i + 1) + " is completed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "InstallThread");

        long now = System.currentTimeMillis();
        Thread threadMonitor = new Thread(() -> {
            while (thread.isAlive()) {
                try {
                    Thread.sleep(1000);

                    if (System.currentTimeMillis() - now > 8000) {
                        thread.interrupt();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(thread.getName() + " thread starting...");
        thread.start();
        threadMonitor.start();

        try {
            thread.join(); // The main thread waits for this thread to finish
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (!thread.isInterrupted()) {
            installThread.start();
        } else {
            System.out.println("Thread was interrupted. Not starting " + installThread.getName());
        }
    }
}
