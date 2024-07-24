import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        var dtf = DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.MEDIUM,
                FormatStyle.LONG
        );

        Callable<ZonedDateTime> waitThenDoIt = () -> {
          ZonedDateTime zdt = null;
          try {
              TimeUnit.SECONDS.sleep(2);
              zdt = ZonedDateTime.now();
          } catch (InterruptedException e) {
              throw new RuntimeException(e);
          }
          return zdt;
        };

//        try (var threadPool = Executors.newFixedThreadPool(2)) {
//            List<Callable<ZonedDateTime>> list = Collections.nCopies(4, waitThenDoIt);
//            System.out.println("\n----> " + ZonedDateTime.now().format(dtf));
//            List<Future<ZonedDateTime>> futureList = threadPool.invokeAll(list);
//            for (Future<ZonedDateTime> result : futureList) {
//                System.out.println(result.get(1, TimeUnit.SECONDS).format(dtf));
//            }
//        } catch (InterruptedException | TimeoutException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }

        Runnable dateTask = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println( "a " +
                    ZonedDateTime.now().format(dtf)
            );
        };

        System.out.println("\n----> " + ZonedDateTime.now().format(dtf));
        // newScheduledThreadPool ---> is a cachedThreadPool
        try (ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5)) {
//            for (int i = 0; i < 4; i++) {
//                scheduledExecutorService.schedule(() -> System.out.println(
//                                ZonedDateTime.now().format(dtf)
//                        ),
//                        2 * (i + 1), // the delay is managed by the executor
//                        TimeUnit.SECONDS
//                );
//            }
//            // schedules the next task after the first finishes not after every 2s
//            var scheduledTask = scheduledExecutorService.scheduleWithFixedDelay(
//                    dateTask,
//                    2,
//                    2, // the delay is managed by the executor
//                    TimeUnit.SECONDS
//            );
            // schedules the next task after every 3s
            var scheduledTask = scheduledExecutorService.scheduleAtFixedRate(
                    dateTask,
                    2,
                    2, // the delay is managed by the executor
                    TimeUnit.SECONDS
            );
            // keep checking the state of the future task
            long time = System.currentTimeMillis();
            while (!scheduledTask.isDone()) {
                TimeUnit.SECONDS.sleep(2);
                if ((System.currentTimeMillis() - time) / 1000 > 10) {
                    scheduledTask.cancel(true);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
