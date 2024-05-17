import java.util.Scanner;
import java.util.Arrays;

public class Matrix {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int[][] matrix = new int[2][2];

        // (x, y)
        // (0, 0) (1, 0)  4, 3
        // (0, 1) (1, 1)  5, 6

        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                matrix[x][y] = scanner.nextInt();
            }
        }

        int determinant = determinant(matrix);
        System.out.println("Determinant = " + determinant);

        double[][] adjoint = new double[][] {{6, -3}, {-5, 4}};
        System.out.println("Before = " + Arrays.deepToString(adjoint));

        for (int x = 0; x < adjoint.length; x++) {
            for (int y = 0; y < adjoint.length; y++) {
                double inverse = (double) 1/determinant * adjoint[x][y];
                adjoint[x][y] = inverse;
            }
        }
        System.out.println("Inverse = " + Arrays.deepToString(adjoint));
    }

    public static int determinant(int[][] arr) {
        return (arr[0][0] * arr[1][1]) - (arr[0][1] * arr[1][0]);
    }
}
