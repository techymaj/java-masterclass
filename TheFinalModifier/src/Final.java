public class Final {

    public final void me() {
        System.out.println("I AM");
    }

    public void overrideMe() {}

    // When you make a static method final, a subclass can't hide it
    public static void iAm() {
        System.out.println("We are");
    }
}

class X extends Final {

    public static void main(String[] args) {
        X x = new X();
        x.iAm("hi");
    }

    // this is method hiding
    public static void iAm() {
        System.out.println("Hidden");
    }

    public static void iAm(final String x) {
        System.out.println(x + "!");
    }
}


record Teller(String name, int age, int cash) {
    Teller(String name, int age, int cash) {
        this.name = name;
        this.age = age;
        this.cash = cash;
    }
}