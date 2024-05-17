import java.util.Arrays;
import java.util.Scanner;

public class Matrix {

    public static void main(String[] args) {

        int[][] matrix = new int[2][2];
        Scanner scanner = new Scanner(System.in);
        // (0, 0) (1, 0)    4, 5
        // (0, 1) (1, 1)    3, 6
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                System.out.println("Enter a value: ");
                matrix[x][y] = scanner.nextInt();
            }
        }

        System.out.println(Arrays.deepToString(matrix));

        int determinant = determinant(matrix);
        System.out.println("Determinant = " + determinant);
    }

    public static int determinant(int[][] arr) {
        return (arr[0][0] * arr[1][1]) - (arr[0][1] * arr[1][0]);
    }
}
