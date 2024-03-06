import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        Consumer<String> print = (String s) -> {
            System.out.println(s);
            var t = s.transform(String::toUpperCase).transform(String::toUpperCase);
            System.out.println(t);
        };
//        print.accept("Hello, World!");

        // identifying the type of lambda to write
        Runnable r = () -> System.out.println("Hello, World!");
        r.run(); // an operation that takes no arguments and returns no result

        Predicate<String> pred = String::isEmpty;
        System.out.println(pred.test("Hello, World!")); // a function that takes an argument and returns a boolean result

        Consumer<String> c = System.out::println;
        c.accept("Hello, World!"); // an operation that takes an argument and returns no result

        // finding the right method to implement
        // return true for strings of characters that have exactly 3 characters

        Predicate<String> p =
                (String s) -> {
                    var x = 4;
                    x++;
                    return false;
                };

        System.out.println(p.test("Hello"));

        var strings = List.of("Well", "I am", "I will", "HER");
        Main main = new Main();
        var retained = main.retainStringsOfLength3(strings);
        System.out.println(retained);

        MyConsumer<String> consumer = (String s) -> {
            var lower = s.toLowerCase();
            System.out.print(lower);
        };
        consumer.accept("I Am ");

        MyConsumer<String> causality = System.out::println;
        causality.accept("Accepted");

        MyConsumer<String> joiner = consumer.andThen(causality);
        joiner.accept("Casuality");


    }

    List<String> retainStringsOfLength3(List<String> strings) {

        Predicate<String> predicate = s -> s.length() == 3;
        List<String> stringsOfLength3 = new ArrayList<>();
        for (String s: strings) {
            if (predicate.test(s)) {
                stringsOfLength3.add(s);
            }
        }
        return stringsOfLength3;
    }

    int calculateTotalPrice(List<Product> products) {
//        int totalPrice = 0; lambdas cannot modify variables defined outside their scope

        Consumer<Product> consumer =
                product -> {
                    int totalPrice = 0;
                    totalPrice += product.getPrice();
                };
        for (Product product: products) {
            consumer.accept(product);
        }

        return -1;
    }

}
