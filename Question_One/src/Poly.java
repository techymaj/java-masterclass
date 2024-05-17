public abstract class Poly {

    public abstract void one();
}

class PolyA extends Poly {

    @Override
    public void one() {
        System.out.println("We are one");
    }
}

class PolyB extends Poly {
    @Override
    public void one() {
        System.out.println("The One");
    }
}
