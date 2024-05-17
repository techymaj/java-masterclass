public interface C extends D, E, F {
    // abstract methods any other constants
    public abstract void hit();
}

interface D {
// abstract methods
    public abstract void run();
}

interface E {
// abstract methods
    public abstract void eat();
}

interface F {
// abstract methods
    public abstract void walk();
}

class Z implements C {
   // Override all the abstract methods in C, D, E, F
    @Override
    public void hit() {

    }

    @Override
    public void run() {

    }

    @Override
    public void eat() {

    }

    @Override
    public void walk() {
        
    }
}
