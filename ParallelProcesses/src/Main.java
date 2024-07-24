import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class RecursiveSumTask extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;
    private final int division;

    public RecursiveSumTask(long[] numbers, int start, int end, int division) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.division = division;
    }

    @Override
    protected Long compute() {
        if ((end - start) <= (numbers.length / division)) {
            System.out.println(start + " : " + end);
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += numbers[i];
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            RecursiveSumTask leftTask = new RecursiveSumTask(numbers, start, mid, division);
            RecursiveSumTask rightTask = new RecursiveSumTask(numbers, mid, end, division);
            leftTask.fork();
            rightTask.fork();
            return leftTask.join() + rightTask.join();
        }
    }
}

public class Main {

    public static void main(String[] args) {

        int numbers_length = 100_000;
        long[] numbers = new Random().longs(
                numbers_length,
                1,
                numbers_length
        ).toArray();
        long sum = Arrays.stream(numbers).sum();
        System.out.println("sum = " + sum);

        try (ForkJoinPool threadPool = ForkJoinPool.commonPool()) {
            List<Callable<Long>> tasks = new ArrayList<>();
            int taskNo = 10;
            int splitCount = numbers_length / taskNo;
            for (int i = 0; i < taskNo; i++) {
                int start = i * splitCount;
                int end = start + splitCount;
                tasks.add(() -> {
                    long tasksum = 0;
                    for (int j = start; j < end; j++) {
                        tasksum += numbers[j];
                    }
                    return  tasksum;
                });
            }
            List<Future<Long>> futureList = threadPool.invokeAll(tasks);
            System.out.println("CPUs = " + Runtime.getRuntime().availableProcessors());
            System.out.println("Parallelism = " + threadPool.getParallelism());
            System.out.println("Pool size = " + threadPool.getPoolSize());
            System.out.println("Steal count = " + threadPool.getStealCount());

            long taskSum = 0;
            for (Future<Long> future : futureList) {
                taskSum += future.get();
            }
            System.out.println(threadPool.getClass().getName() + " Threadpool sum = " + taskSum);

            RecursiveTask<Long> task = new RecursiveSumTask(numbers, 0, numbers_length, 8);
            long forkJoinSum = threadPool.invoke(task);
            System.out.println("RecursiveTask sum is: " + forkJoinSum);

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
