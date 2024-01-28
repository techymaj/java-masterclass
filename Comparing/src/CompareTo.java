public class CompareTo implements Comparable<Integer> {

    private int a;
    private int b;

    public CompareTo(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public static void main(String[] args) {
        CompareTo compareTo = new CompareTo(10, 20);
        int result = compareTo.compareTo(10);
    }

    @Override
    public int compareTo(Integer o) {
        return Integer.compare(a, b);
    }
}
