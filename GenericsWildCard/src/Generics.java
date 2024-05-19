import java.util.List;

public class Generics {

    public static void main(String[] args) {
        List<? super Y> list = List.of(new Y(5, "John"), new X("Alex"));
        List<? extends Y> listToo = List.of(new Y(5, "John"), new X("Alex"));
    }
}

class X {
    String name = "John";

    public X(String name) {
        this.name = name;
    }
}

class Y extends X {
    int age = 5;
    String name = "John";

    public Y(int age, String name) {
        super(name);
        this.age = age;
    }
}