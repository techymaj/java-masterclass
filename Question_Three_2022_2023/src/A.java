public interface A {
    // No
    // Reason - interfaces are incomplete and abstract classes
    public static final int age = 2;
    public abstract void eat();

    public default void run() {}
    public static void walk() {}
}

class B {

    public static void main(String[] args) {

//        A a = new A();
    }
}


