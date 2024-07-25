import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class VisitorList {

    private static final CopyOnWriteArrayList<Person> masterList;

    static {
        masterList = Stream.generate(Person::new)
                .distinct()
                .limit(2500)
                .collect(CopyOnWriteArrayList::new,
                        CopyOnWriteArrayList::add,
                        CopyOnWriteArrayList::addAll
                );
    }

    private static final ArrayBlockingQueue<Person> visitors = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) {
        Runnable producer = () -> {
            Person visitor = new Person();
            System.out.println("Queueing " + visitor);
            boolean queued;
            try {
                queued = visitors.offer(visitor, 10, TimeUnit.SECONDS);
//                System.out.println(visitors.size() + " --> " + visitors);
//                if (!queued) {
//                    System.out.println("Queue is full, cannot add " + visitor);
//                    System.out.println("Draining Queue and writing Visitors to file...");
//                    List<Person> tempList = new ArrayList<>();
//                    visitors.drainTo(tempList);
//                    List<String> lines = new ArrayList<>();
//                    tempList.forEach(customer -> lines.add(customer.toString()));
//                    lines.add(visitor.toString()); // current visitor, not added to queue
//                    Files.write(
//                            Path.of("DrainedQueue.txt"),
//                            lines,
//                            StandardOpenOption.CREATE,
//                            StandardOpenOption.APPEND
//                    );
//                }
            } catch (InterruptedException e) {
                System.out.println("Queue is full, cannot add " + visitor);
            }
        };

        Runnable consumer = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " Polling Queue " + visitors.size());
            Person visitor = visitors.poll();
            if (visitor != null) {
                System.out.println(threadName + " " + visitor);
                if (!masterList.contains(visitor)) {
                    masterList.add(visitor);
                    System.out.println("-->New visitor gets coupon!: " + visitor);
                }
            }
            System.out.println(threadName + " done " + visitors.size());
        };

        try (
                ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
                ScheduledExecutorService consumerPool = Executors.newScheduledThreadPool(3)
        ) {
            scheduledExecutorService.scheduleAtFixedRate(
                    producer,
                    0,
                    1,
                    TimeUnit.SECONDS
            );
            boolean _ = scheduledExecutorService.awaitTermination(10, TimeUnit.SECONDS);

            for (int i = 0; i < 3; i++) {
                consumerPool.scheduleAtFixedRate(
                        consumer, 6, 3, TimeUnit.SECONDS
                );
            }
            boolean _ = consumerPool.awaitTermination(20, TimeUnit.SECONDS);

        } catch (InterruptedException ignored) {

        }
    }
}
