public abstract class Poly {

    public abstract void one();
}

class PolyA extends Poly {

    @Override
    public void one() {
        System.out.println("One");
    }
}

class PolyB extends Poly {

    @Override
    public void one() {
        System.out.println("Another one");
    }

    public static void main(String[] args) {
        PolyB polyB = new PolyB();
        polyB.one();

        PolyA polyA = new PolyA();
        polyA.one();
    }
}
