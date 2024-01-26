import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class MethodReferences {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>(List.of("John", "Freddy", "Samuel", "Ferdinand"));
        names.forEach(System.out::println);

        calculator(Integer::sum, 3, 5);
        calculator(Double::min, 3.0, 5.0);
        calculator(String::concat, "Thor ", "Odinson");

        // static method reference
        // In a static method reference, you reference a static method from a class.
        DoubleUnaryOperator op = Math::sqrt;
        System.out.println(op.applyAsDouble(4));

        // bound method reference
        Consumer<String> consumeMe = System.out::println;
        consumeMe.accept("Consumed");

        // Unbound method reference
        // In an unbound method reference, you reference a non-static method
        // from a class or an instance of a class.
        ToIntFunction<String> toInt = String::length;
        int result = toInt.applyAsInt("54321");
        System.out.println(result);

        // constructor method references
        Supplier<List<ArrayList<Integer>>> arrayListGenerator = ArrayList::new;
        arrayListGenerator.get();

        Supplier<PlainOld> supplier = PlainOld::new;
        // a method reference, like a lambda expression variable is lazy
        // the method is not called until the get() method is called
        supplier.get();

        Supplier<PlainOld> reference = PlainOld::new;
        PlainOld pojo = reference.get();

        System.out.println("Getting array");
        PlainOld[] pojos = seedArray(PlainOld::new, 5);
        System.out.println(Arrays.toString(pojos));

        // String concatenated = String::concat; // String is not a functional interface, won't compile
        StringButFunctional concatenated = String::concat;
        String s = concatenated.join("Oh My ", "GOD!!!");
        System.out.println(s);

        // Non-static method cannot be referenced from a static context
//        UnaryOperator<String> concatenatedToo = String::concat;

        // UnaryOperator extends Function which declares apply()
        UnaryOperator<String> concatenatedToo = str -> str + "!";
        System.out.println(concatenatedToo.apply("I get it now"));

        // won't compile. expected one param, found 2
//        UnaryOperator<String> unoReversal = (String str1, String str2) -> str1.concat(str2);

        UnaryOperator<String> upperCased = String::toUpperCase; // can only use lambdas on functional interfaces
        String usurped = upperCased.apply("usurper");
        System.out.println(usurped);

        ToIntFunction<Double> value = Double::intValue;
        int v = value.applyAsInt(34.54);
        System.out.println(v);


    }

    @FunctionalInterface
    interface StringButFunctional {
        String join(String s1, String s2);
    }

    private static <T> void calculator(BinaryOperator<T> binaryOperator, T t1, T t2) {
        T result = binaryOperator.apply(t1, t2);
        System.out.println("Result of operation: " + result);
    }

    private static PlainOld[] seedArray(Supplier<PlainOld> reference, int count) {
        PlainOld[] arr = new PlainOld[count];
        //  initialize each element of the array with objects supplied by a Supplier<PlainOld>
        Arrays.setAll(arr, i -> reference.get());

        return arr;
    }
}

class PlainOld {
    private static int last_id = 1;
    private int id;
    public String name;

    public PlainOld() {
        id = last_id++; // assign and then increment
        System.out.println("creating a plain old object with id " + id);
    }

    @Override
    public String toString() {
        return getClass().getName() + " " + id;
    }
}

