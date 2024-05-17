public class C implements IC {
    public static void main(String[] args) {
        C c = new C();
        c.eat();
    }

    @Override
    public void eat() {
        System.out.println("Eating");
    }
}

interface IC {
    public void eat();
}

class Z implements IC {
    @Override
    public void eat() {
        System.out.println("Death valley");
    }

    public static void main(String[] args) {
        Z z = new Z();
        z.eat();
    }
}

