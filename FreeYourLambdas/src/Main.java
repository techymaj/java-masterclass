public class Main {

    public static void main(String[] args) {
        Main main = new Main();

        MyConsumer<String> consumer = Main::test2;

        MyConsumer<String> consumers = main::test;

        MyConsumer<String> consumers2 = consumer.andThen(consumers);
        consumers2.accept("");
    }

    public void test(String s) {
        System.out.println("Hello Instance");
    }

    public static void test2(String s) {
        System.out.println("Hello Static");
    }
}
