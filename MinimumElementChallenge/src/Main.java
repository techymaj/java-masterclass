import java.util.Arrays;

public class Main {
    public static void main(String... args) {
        String[] arr = readIntegers(args);
        int[] ints = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            ints[i] = Integer.parseInt(arr[i].trim());
        }

        System.out.println("Original: " + Arrays.toString(arr));
        System.out.println("Reversed: " + Arrays.toString(reverse(ints)));
        System.out.println("Minimum value is: " + findMin(ints));
    }

    private static String[] readIntegers(String... string) {

        for (String s : string) {
            return s.split(",");
        }

        return new String[0];
    }

    private static int findMin(int[] arr) {

        Arrays.sort(arr);

        return arr[0];
    }

    private static int[] reverse(int[] arr) {
//        Arrays.sort(arr);
        int temp;
        int maxIndex = arr.length - 1;

        for (int i = 0; i < arr.length / 2; i++) {
            temp = arr[i];
            arr[i] = arr[maxIndex - i];
            arr[maxIndex - i] = temp;
        }
        return arr;
    }
}
