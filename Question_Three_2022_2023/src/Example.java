public interface Example {

    public abstract void hit();

    public static void run() {
        System.out.println("This is static");
    }
}

class ClassOne implements Example {

    @Override
    public void hit() {
        System.out.println("I've been hit");
    }

    public static void main(String[] args) {
        ClassOne object = new ClassOne();
        object.hit();
        Example.run();
    }
}

class ClassTwo implements Example {

    @Override
    public void hit() {
        System.out.println("Hit n Run");
    }

    public static void main(String[] args) {

        ClassTwo object = new ClassTwo();
        object.hit();
        Example.run();
    }
}

// Output is:
// I've been hit
// This is static
// Hit n Run
// This is static









