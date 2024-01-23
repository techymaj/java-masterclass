public class OuterClass {

    final private static String named = "Denis";

    // inner class, declared in class body
    class InnerClass implements IAM {
        final String name = "jax";

        public void mainer() {
            // compiler error: cannot instantiate Instance inner from with-in
//            InnerClass in = new InnerClass();
//            in.name;
            OuterClass obj = new OuterClass();
            OuterClass.InnerClass m = obj.new InnerClass();
            m.me();
        }
    }

    interface IAM {
        default void me() {
            System.out.println("I am " + named);
        }
    }

}

class Main {
    public static void main(String[] args) {
//        // access is through an instance of the outer class
        OuterClass obj = new OuterClass();
        OuterClass.InnerClass inner = obj.new InnerClass();
        inner.me();
//        String s = inner.name;
//        System.out.println(s);
    }
}
