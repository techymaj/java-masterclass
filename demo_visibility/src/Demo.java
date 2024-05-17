public class Demo {

    public int age;

    public static void main(String[] args) {
        Demo demo = new Demo();
        System.out.println(demo.age);
    }
}

class D extends Demo {

    public static void main(String[] args) {
        D d = new D();
        System.out.println(d.age);
    }
}
