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

    // in this case, due to type erasure, String is erased and replaced with Object
    // because Comparable<String extends java.lang.Object> implicitly
//    @Override
//    public int compareTo(Object o) {
//        return 0;
//    }

    @Override
    public int compareTo(String o) {
        return a.compareTo(o);
    }

}

