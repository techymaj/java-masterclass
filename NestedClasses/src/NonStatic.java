public class NonStatic {

    // inner class, declared in class body
    class NonStaticInner {
        final String name = "jax";
    }

    public static void main(String[] args) {
        // access is through an instance of the outer class
        NonStatic inner = new NonStatic();
        NonStatic outer = inner;
//        System.out.println(outer.name);
    }
}
