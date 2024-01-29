public class Printer <T> {

    T value;

    public Printer(T value) {
        this.value = value;
    }

    public void print() {
        System.out.println(value);
    }

    public static void main(String[] args) {
        Printer<String> printer = new Printer<>("Hello World!");
        Printer<Integer> printer2 = new Printer<>(123);
        Printer<Double> printer3 = new Printer<>(123.456);
        int sum = printer2.value + printer3.value.intValue();

        printer.print();
        System.out.println(sum);
    }
}
