import java.util.Objects;

@FunctionalInterface
public interface MyConsumer<T> {
    void accept(T t);

    default MyConsumer<T> andThen(MyConsumer<T> andThen) {
        Objects.requireNonNull(andThen);
        return (T t) -> {
            accept(t);
            andThen.accept(t);
        };
    }
}
