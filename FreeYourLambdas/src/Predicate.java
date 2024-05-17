import java.util.Objects;

public class Predicate {

    public static void main(String[] args) {

        MyPredicate<String> p1 = s -> s == null;
        System.out.println(p1.test(null));;
        MyPredicate<String> p2 = s -> s.length() < 10;
//        System.out.println(p2.test("I am"));;
        System.out.println(p1.negate().test(null));
//        MyPredicate<String> p3 = p1.negate();
//        System.out.println(p3.test(""));
    }
}

@FunctionalInterface
interface MyPredicate<T> {
    boolean test(T t);

    default MyPredicate<T> and(MyPredicate<T> other) {
        Objects.requireNonNull(other);
        // this.test(t) ----> The predicate i'm in
        // other.test(t) ----> The other predicate
        return (T t) -> this.test(t) && other.test(t);
    }

    default MyPredicate<T> negate() {
        return (T t) -> !this.test(t);
    }
}
