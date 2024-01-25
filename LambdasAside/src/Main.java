public class Main {

    public static void main(String[] args) {
        // a lambda is a shortcut to defining an implementation of a functional interface
        printable(() -> "Holla" + "!");
    }

    private static void printable(Printable<String> printer) {
        System.out.println(printer.print());
    }
}
