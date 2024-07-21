public class Main {

    private volatile boolean flag = false;

    public void toggle() {
        flag = !flag;
    }

    boolean isReady() {
        return flag;
    }

    public static void main(String[] args) {
        Main main = new Main();

        Thread t1 = new Thread(() -> {
            try {
                // create delay to make t2 wait
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            main.toggle();
            System.out.println("A. Ready: " + main.isReady());
        });

        Thread t2 = new Thread(() -> {

//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

            while (!main.isReady()) {

            }
            System.out.println("B. Ready: " + main.isReady());
        });

        t1.start();
        t2.start();
    }
}
