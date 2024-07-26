import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class AtomicId {
    private final AtomicInteger nextID = new AtomicInteger(1);

    public int getNextId() {
        return nextID.getAndIncrement();
    }
}
public class AtomicClasses {
    static AtomicId id = new AtomicId();
    static Callable<AtomicId> increments = () -> {

        System.out.println(Thread.currentThread().getName()
                + " increments to --> " + id.getNextId()
        );
        return null;
    };

    public static void main(String[] args) throws InterruptedException {

        try (var mEx = Executors.newCachedThreadPool()) {
            var futures = Collections.nCopies(10, increments);
            mEx.invokeAll(futures);
        }
    }
}
