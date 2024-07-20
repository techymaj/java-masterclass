public class Main {

    public static void main(String[] args) {

        Thread threadOne = new ThreadOne();
        Thread threadTwo = new Thread(ThreadTwo::printOdd);

        threadOne.start();
        threadTwo.start();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        threadOne.interrupt();
    }
}
