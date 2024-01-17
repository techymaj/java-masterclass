import java.util.ArrayList;

public class Store {

    private ArrayList<Product> sales;
    private ArrayList<OrderItem> orders;
    public static void main(String[] args) {

    }

    public void addItem(String item, double price, int quantity) {
        orders.add(new OrderItem(item, price, quantity));
    }

    public void printOrderedItems() {
        for (var item : orders) {
            System.out.println(
                    item.name() + " : " + " UGX" +
                            item.price() + " " +
                            item.quantity());
        }
    }

}
