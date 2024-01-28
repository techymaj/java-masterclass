import static java.lang.Integer.compare;

public class Main {

    public static void main(String[] args) {

        int a = 10;
        int b = 20;

        int result = compare(a, b);  // static import
        int result2 = compared(a, b);  // static method
        System.out.println(result);
        System.out.println(result2);
    }

    public static int compared(int a, int b) {
        return Integer.compare(a, b);
    }
}
