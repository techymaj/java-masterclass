public class CachedData {

    private volatile boolean flag = false;

    public void toggle() {
        flag = !flag;
    }

    public boolean isReady() {
        return flag;
    }

    public static void main(String[] args) {

        CachedData data = new CachedData();

        Thread writer = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.toggle();
            System.out.println("A. Flag set to " + data.isReady());
        });

        Thread reader = new Thread(() -> {
            while (!data.isReady()) {
//                System.out.println("Reader thread is waiting...");
            }
            System.out.println("B. Flag is " + data.isReady());
        });

        writer.start();
        reader.start();
    }
}
