public class Example {

    static int age; // class variable - it belongs to the class
    static final int height = 174; // instance variable or instance field
    // static - there's only one copy in memory

    public Example() {

    }

    public static void main(String[] args) {
        System.out.println(Example.height);
        Example obj2 = new Example();
        System.out.println("Object 2: " + obj2.height); // 0
    }
}
