public class C implements A, B {

    @Override
    public void mA(String value) {
        System.out.println("My value is: " + value);
        System.out.println("My constant is: " + A.K);
    }

    @Override
    public double mB(int x) {
        System.out.println("Passed value: " + x);
        System.out.println("My constant is: " + B.K);
        return x;
    }

    public static void main(String[] args) {
        C obj = new C();
        obj.mA("Johnny");
        obj.mB(2);
        obj.mD();
    }
}
