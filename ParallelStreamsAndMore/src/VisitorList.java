import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VisitorList {

    private static final ArrayBlockingQueue<Person> visitors = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) {
        Runnable producer = () -> {
            Person visitor = new Person();
            System.out.println("Adding " + visitor);
            boolean queued;
            try {
                queued = visitors.offer(visitor, 3, TimeUnit.SECONDS);
                System.out.println(visitors.size() + " --> " + visitors);
                if (!queued) {
                    System.out.println("Queue is full, cannot add " + visitor);
                    System.out.println("Draining Queue and writing Visitors to file...");
                    List<Person> tempList = new ArrayList<>();
                    visitors.drainTo(tempList);
                    List<String> lines = new ArrayList<>();
                    tempList.forEach(customer -> lines.add(customer.toString()));
                    lines.add(visitor.toString()); // current visitor, not added to queue
                    Files.write(
                            Path.of("DrainedQueue.txt"),
                            lines,
                            StandardOpenOption.CREATE,
                            StandardOpenOption.APPEND
                    );
                }
            } catch (InterruptedException e) {
                System.out.println("Queue is full, cannot add " + visitor);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        try (ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor()) {
            scheduledExecutorService.scheduleAtFixedRate(
                    producer,
                    0,
                    500,
                    TimeUnit.MILLISECONDS
            );
            boolean _ = scheduledExecutorService.awaitTermination(20, TimeUnit.SECONDS);
        } catch (InterruptedException ignored) {

        }
    }
}
