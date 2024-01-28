public class Man implements Comparable<Integer> {
    private String name;
    private Integer age;

    public Man(String name) {
        this.name = name;
    }

    public Man(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        Man jax = new Man("Jax");
        Man jj = new Man(22);
//        int result = jax.compareTo("Jaxon");
        int result2 = jj.compareTo(22);
//        System.out.println(result);
        System.out.println(result2);
    }

    @Override
    public int compareTo(Integer o) {
        return age.compareTo(o);
    }

}
