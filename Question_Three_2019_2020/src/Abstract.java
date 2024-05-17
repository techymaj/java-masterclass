public abstract class Abstract {

    // An abstract class is an incomplete class that cannot be instantiated
    // class is a blueprint

    // - making the class abstract using the keyword abstract in its declaration
    // - By declaring abstract methods within the class
    // - By extending an abstract class and not implementing any abstract methods
    // - By implementing an interface with any abstract methods and
    // not implementing those abstract methods

    public abstract void eat();

    public static void main(String[] args) {

//        Abstract object = new Abstract(); // You cannot create objects from an incomplete class
    }
}

class X extends Abstract {
    @Override
    public void eat() {

    }
}

abstract class Y implements Z {

}

interface Z {
    public abstract void run();
}