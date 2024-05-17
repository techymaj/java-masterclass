public class Poly implements H {

    @Override
    public void run() {
        System.out.println("This runs");
    }

    public static void main(String[] args) {

        Poly object1 = new Poly();
        object1.run();

        System.out.println("I am " + H.age);
    }
}

interface H {
    public static final int age = 29;
    public abstract void run();
}

class R implements H {

    @Override
    public void run() {
        System.out.println("I run everyday");
    }

    public static void main(String[] args) {

        R obj = new R();
        obj.run();
    }
}