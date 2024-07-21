import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        ShoeWarehouse shoeWarehouse = new ShoeWarehouse();

        Thread producerThread = new Thread(shoeWarehouse::receiveOrder);
        Thread consumerThreadOne = new Thread(shoeWarehouse::fulfillOrder);
        Thread consumerThreadTwo = new Thread(shoeWarehouse::fulfillOrder);

        try {
            TimeUnit.NANOSECONDS.sleep(1);
            System.out.println("================ Receiving orders ================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producerThread.start();

        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("================ Fulfillment ================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        consumerThreadOne.start();
        consumerThreadTwo.start();
    }
}
