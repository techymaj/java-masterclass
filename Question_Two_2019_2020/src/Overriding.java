public abstract class Overriding implements C {

    // Method overriding is the ability of a method to change its implementation (mutability)

    public void eat() {
        System.out.println("eat");
    }
    public abstract void run();
}

class A extends Overriding {
    @Override
    public void eat() {
        System.out.println("I am eating");
    }

    @Override
    public void run() {
        System.out.println("Running with two feet");
    }

    @Override
    public void walk() {
        System.out.println("I walk");
    }
}

interface C {
    public abstract void walk();
}