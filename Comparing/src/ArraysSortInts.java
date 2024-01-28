public class ArraysSortInts {
    public static void main(String[] args) {
        Integer[] ints = { -10, 20, -30, 40, -50 };
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
        System.out.println("After sorting");
        // to use this method, the elements of the array must be of a class
        // that implements the Comparable interface
        // but primitive types are supported too because of the natural ordering
        java.util.Arrays.sort(ints);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}
