public class Generic<T> {

    public static void main(String[] args) {
        var generic = new Generic<>("jax");
        generic.describe();

        var generic2 = new Generic<>(24);
        generic2.describe();
    }
    private T value;

    public Generic(T name) {
        this.value = name;
    }

    public void describe() {
        System.out.println("i am " + this.value);
    }
}
