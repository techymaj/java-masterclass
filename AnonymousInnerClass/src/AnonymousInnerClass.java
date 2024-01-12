public class AnonymousInnerClass {
    public static void main(String[] args) {
        Runnable myAnonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Running...");
            }
        };
        myAnonymousRunnable.run();
    }
}
