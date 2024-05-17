public class A {

    public int age(int age) {
        return age;
    }
}

class B extends A {

    @Override
    public int age(int age) {
        return age * 2;
    }

    public static void main(String[] args) {
        B b = new B();
        int doubleAge = b.age(30);
        System.out.println(doubleAge);
    }
}
