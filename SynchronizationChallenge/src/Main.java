import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        ShoeWarehouse shoeWarehouse = new ShoeWarehouse();

        try (
                var producerThread = Executors.newSingleThreadExecutor();
                var consumerThreads = Executors.newCachedThreadPool()
        ) {
            TimeUnit.NANOSECONDS.sleep(1);
            System.out.println("\n================ Receiving orders ================");
            producerThread.execute(() -> shoeWarehouse.receiveOrder(15));
            TimeUnit.SECONDS.sleep(1);
            System.out.println("\n================ Fulfillment ================");
            consumerThreads.submit(shoeWarehouse::fulfillOrder);
            consumerThreads.submit(shoeWarehouse::fulfillOrder);
        } catch (InterruptedException ignore) {

        }
    }
}
