import java.util.Objects;

public class Consumer {

    public static void main(String[] args) {

        MyConsumer<String> eating = t -> System.out.println("Eating " + t);
        eating.accept("chips");

        MyConsumer<String> drinking = t -> System.out.println("Drinking " + t);
        drinking.accept("coke");

        MyConsumer<String> times = t -> System.out.println(t + " times");
//        times.accept("5");

        MyConsumer<String> whatDoYouDo = eating.andThen(drinking).andThen(times);
        whatDoYouDo.accept("healthy meals: ");

    }
}

@FunctionalInterface
interface MyConsumer<T> {

    void accept(T t);

    default MyConsumer<T> andThen(MyConsumer<T> other) {
        Objects.requireNonNull(other);
        return (T t) -> {
            this.accept(t);
            System.out.println("And then");
            other.accept(t);
        };
    }
}
