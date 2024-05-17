public class CustomOverride {

    void one() {
        System.out.println("one");
    }
}

class Overridden extends CustomOverride {

    @Override
    void one() {
        System.out.println("Overridden");
    }
}
