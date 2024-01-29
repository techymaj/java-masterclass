public class CompareTo implements Comparable<String> {

    private String a;

    public CompareTo(String a) {
        this.a = a;
    }

    public static void main(String[] args) {
        Comparable<String> num = new CompareTo("Jax");
        int result = num.compareTo("Jax");
        System.out.println(result);
    }

    @Override
    public int compareTo(String o) {
        return a.compareTo(o);
    }
}
