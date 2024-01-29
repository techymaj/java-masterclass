import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int[] array = new int[]{1, 2, 3, 4, 5};
        Arrays.setAll(array, i -> array[i] * 2); // in place
        System.out.println(array); // reference
        System.out.println(Arrays.toString(doubleArray(array))); // [4, 8, 12, 16, 20]
    }

    public static int[] doubleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] * 2;
        }
        return array;
    }

    @Override
    public String toString() {
        return "Main{}";
    }
}
