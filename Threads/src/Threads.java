import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class Threads {

    public static void main(String[] args) {

//        var thread_one = new Thread();
//        var thread_two = new Thread(() -> {
//            System.out.println("Thread two");
//        });

        CustomThread customThread = new CustomThread();
        customThread.start();

        Runnable runnable = () -> {
            // this code goes in the run method of Runnable
            for (int i = 0; i < 5; i++) {
                System.out.print("2");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        for (int i = 0; i < 5; i++) {
            System.out.print("0");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

//        System.out.println(thread_one);
//        System.out.println(thread_two);
//        printThreadState(thread_one);
//        printThreadState(thread_two);
    }

    public static void printThreadState(Thread thread) {
        System.out.println("-----------------");
        System.out.println("Thread ID: " + thread.getId());
        System.out.println("Thread name: " + thread.getName());
        System.out.println("Thread state: " + thread.getState());
        System.out.println("Thread priority: " + thread.getPriority());
        System.out.println("Thread is alive: " + thread.isAlive());
        System.out.println("Thread group: " + thread.getThreadGroup());
    }
}
