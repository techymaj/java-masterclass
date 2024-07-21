import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShoeWarehouse {

    public final Object orderLock = new Object();
    private static List<Order> orders;
    static List<String> shoeTypes = List.of("Bata", "Timberland", "Gucci");
    static Random shoeType = new Random(3);

    public ShoeWarehouse() {
        var type = shoeTypes.get(shoeType.nextInt(0, 3));

        Random random = new Random();
        var qty = random.nextInt(1, 11);
        orders = IntStream.iterate(1, i -> i + 1)
                .limit(10)
                .mapToObj(i -> new Order("order" + i, type, qty))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public synchronized void receiveOrder() {
        while (orders.size() > 10) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        orders.forEach(order -> System.out.println("Received order: " + order.orderId()));
        notifyAll();
    }

    public synchronized void fulfillOrder() {
        while (orders.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        List<Order> batch = new ArrayList<>();
        synchronized (orderLock) {
            for (int i = 0; i < 5 && !orders.isEmpty(); i++) {
                batch.add(orders.removeFirst());
            }
            for (Order order : batch) {
                System.out.println(Thread.currentThread().getName() + " ---> Fulfilled order: " + order.orderId());
            }
        }
        notifyAll();
    }
}
