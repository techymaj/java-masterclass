public class Lambdas {

    public static void main(String[] args) {

        // Lambda expression
        Printable printer = s -> s + "!";
        String s = printer.print("Hello World");
        System.out.println(s);
    }
}
