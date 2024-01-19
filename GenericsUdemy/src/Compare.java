import java.util.List;

public class Compare {
    public static void main(String[] args) {
        String[] fruits = {"banana", "papple", "mango"};
        String pine = "pineapple";
        int val;
        int in, out;

        for (var fruit : fruits) {
            val = fruit.compareTo(pine);
            in = (int) fruit.charAt(0);
            out = (int) pine.charAt(0);
            System.out.println("In = " + in + " Out = " + out + " Diff = " + (in - out));
            System.out.println("compareTo = " + val);
        }
    }
}
