import java.util.Arrays;

public abstract class A {

    public static void main(String[] args) {

//        A x = new A();

        A y[] = new A[3]; // Can be used to create an array
        y[0] = new A() {

        };

        y[1] = new A() {
        };

        System.out.println(Arrays.toString(y));
    }
}
