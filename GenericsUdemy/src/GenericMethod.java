
// <T> here is different from <T> in describe
public class GenericMethod <T> {

    T value;

    public GenericMethod(T value) {
        this.value = value;
    }

    public static <T> void describe(T item) {
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
