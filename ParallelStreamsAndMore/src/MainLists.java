import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Stream;

public class MainLists {

    public static void main(String[] args) {

        var threadMap = new ConcurrentSkipListMap<String, Long>();
        var persons = Stream.generate(Person::new)
                .limit(10_000)
                .parallel()
                .peek(person -> {
                    var currThreadName = Thread.currentThread().getName()
                            .replace("ForkJoinPool.commonPool-worker-", "thread_");
                    threadMap.merge(currThreadName, 1L, Long::sum);

                })
                .toArray(Person[]::new);

        System.out.println("Total number of array elements " + persons.length);
        System.out.println(threadMap);

        long total = 0;

        for (long count : threadMap.values()) {
            total += count;
        }
        System.out.println(total);
    }
}
