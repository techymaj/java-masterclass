public interface B {

    double K = 2.5;

    double mB(int x);

    default void mD() {
        System.out.println("I am a student");
    }
}
