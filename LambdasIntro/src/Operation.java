@FunctionalInterface
public interface Operation <T> {

    T operate(T t1, T t2);
}
