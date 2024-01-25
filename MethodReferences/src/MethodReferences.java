import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public class MethodReferences {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>(List.of("John", "Freddy", "Samuel", "Ferdinand"));
        names.forEach(System.out::println);

        calculator(Integer::sum, 3, 5);
        calculator(Double::min, 3.0, 5.0);
        calculator(String::concat, "Thor ", "Odinson");

        Supplier<PlainOld> supplier = PlainOld::new;
        // a method reference, like a lambda variable is lazy
        // the method is not called until the get() method is called
        supplier.get();
    }

    private static <T> void calculator(BinaryOperator<T> binaryOperator, T t1, T t2) {
        T result = binaryOperator.apply(t1, t2);
        System.out.println("Result of operation: " + result);
    }
}

class PlainOld {
    private static int last_id = 1;
    private int id;
    public PlainOld() {
        id = last_id++; // assign and then increment
        System.out.println("creating a plain old object with id " + id);
    }
}
