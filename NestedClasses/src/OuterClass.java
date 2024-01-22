public class OuterClass {

    // inner class, declared in class body
    class InnerClass {
        final String name = "jax";
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
