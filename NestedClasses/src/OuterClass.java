public class OuterClass {

    // inner class, declared in class body
    class InnerClass {
        final String name = "jax";

        public static void main(String[] args) {
            // compiler error: cannot instantiate Instance inner from with-in
//            InnerClass in = new InnerClass();
//            in.name;
        }
    }

}

class Main {
    public static void main(String[] args) {
        // access is through an instance of the outer class
        OuterClass obj = new OuterClass();
        OuterClass.InnerClass inner = obj.new InnerClass();
        String s = inner.name;
        System.out.println(s);
    }
}
