public class Poly {

    public final void poly() {
        System.out.println("I am poly");
    }
}


class PolyB extends Poly {

//    @Override
//    public void poly() {
//        System.out.println("Poly has no name");
//    }

    public static void main(String[] args) {
        PolyB polyB = new PolyB();
        polyB.poly();
    }
}