public class G <T> {
    T value;

    public G(T value) {
        this.value = value;
    }

    void print() {
        System.out.println(this.value);
    }

    <S> void genericPrint(S s) {
        System.out.println(s + " printed");
    }
}

class Main {
    public static void main(String[] args) {
        G<Integer> g = new G<>(23);
        g.print();
        g.genericPrint(32);
        g.genericPrint("I am");
    }
}

class Do {
    void doSomething() {
        System.out.println("Done!");
    }

}

class P extends Do {

}

class D<T extends Do> {
    T thing;

    public static void main(String[] args) {
        D<P> p = new D<>();
        p.thing = new P(); // initialize with new instance of P
        // otherwise get a null pointer exception because thing in thing.doSomething() is null
        p.done();
    }

    void done() {
        thing.doSomething();
    }
}
