public class Static {

    public int age;
    private int year;

    public static void main(String[] args) {
        // can access public & private inner class members
        Static.Inner inner = new Inner();
        inner.name = "Hank";
        inner.value = "Val";
        System.out.println(inner.name + " " + inner.value);

    }

    // static inner class, declared in class body
    static class Inner {
        String name = "jax";
        private String value = "Values";

        public static void main(String[] args) {
            // can access private & public outer class members
            Static stat = new Static();
            int sum = stat.age + stat.year;
            System.out.println(sum);

            // from with-in the static inner class, you can instantiate it
            Inner in = new Inner();
            String s = in.value;
            System.out.println(s);
        }
    }
}
