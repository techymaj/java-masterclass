import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShoeWarehouse {

    private static List<Order> orders;
    List<String> shoeTypes = List.of("Bata", "Timberland", "Gucci");
    Random shoeType = new Random(3);

    public void receiveOrder(int ordersToFulfil) {
        var type = shoeTypes.get(shoeType.nextInt(0, 3));
        Random random = new Random();
        var qty = random.nextInt(1, 11);
        orders = IntStream.iterate(1, i -> i + 1)
                .limit(ordersToFulfil)
                .mapToObj(i -> new Order("order" + i, type, qty))
                .collect(Collectors.toCollection(ArrayList::new));
        orders.forEach(order -> System.out.println(Thread.currentThread().getName() +
                " Received order: " + order.orderId()));
    }

    public void fulfillOrder() {
        var threadName = Thread.currentThread().getName();
        orders.stream()
              .map(_ -> orders.removeFirst())
              .forEach(order -> System.out.println(threadName + " ---> Fulfilled order: " + order.orderId()));
    }
}
