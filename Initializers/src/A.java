public abstract class A {

    public A() {

    }

    abstract void callMe();

    public static void main(String[] args) {

        A a = () -> {
            System.out.println("I am");
        };
        a.callMe();
    }
}

interface B {
    void callMe();
}