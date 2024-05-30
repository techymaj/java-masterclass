import java.util.*;

public class Spiral {
    static Random random = new Random(12);

    public static void main(String[] args) {
        int[][] twoD = create2DArray();
        Map<Integer, int[]> markers = new HashMap<>();
        int[] traversed = new int[12];
        int counter;
        int rows = 0;
        int columns = 0;
        int mark = twoD[rows][columns];

        markers.put(mark, new int[] {rows,columns});


        markers.forEach((i, j) -> {
            System.out.println(i + " maps to: " + Arrays.toString(j));
        });
    }

    public static int[][] create2DArray() {
        int[][] twoD = new int[3][4];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                twoD[i][j] = random.nextInt(1, 11);
            }
        }
        return twoD;
    }
}
