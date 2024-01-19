
// <T> here is different from <T> in describe
public class GenericMethod <T> {
    // In the class GenericMethod<T>, the generic type <T>
    // is declared at the class level. It is associated with instances of
    // the class and can be used throughout the class,
    // including in instance variables and non-static methods.

    T value;

    public GenericMethod(T value) {
        this.value = value;
    }

    public static <T> void describe(T item) {
        // another generic type <T> is declared at the method level.
        // This type parameter is specific to the method and is unrelated to
        // the generic type declared at the class level. It is used only
        // within the scope of the method.
        System.out.println("value is " + item);
    }

    public static void main(String[] args) {

        GenericMethod<String> g = new GenericMethod<>("Jax");
        GenericMethod<Integer> m = new GenericMethod<>(29);
        System.out.println(g.value);
        System.out.println(m.value);
        describe("Jack");
        describe(23);
    }
}
