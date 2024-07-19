import java.util.concurrent.Executor;

public class Threads {

    public static void main(String[] args) {

        var thread_one = new Thread();
        var thread_two = new Thread(() -> {
            System.out.println("Thread two");
        });
        Executor executor = (Runnable command) -> {
                System.out.println("Executor");
            };

        System.out.println(thread_one);
        System.out.println(thread_two);
        printThreadState(thread_one);
        printThreadState(thread_two);
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
