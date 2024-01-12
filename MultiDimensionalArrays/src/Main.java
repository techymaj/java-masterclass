import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] dimensional = new int[4][]; // acceptable because we know the number of rows up front which = null
        System.out.println(Arrays.deepToString(dimensional));
        for (int i = 0; i < dimensional.length; i++) {
            dimensional[i] = new int[] {i, 2 * i, 3};
        }
        System.out.println(Arrays.deepToString(dimensional));

    }
}
