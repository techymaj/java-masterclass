public class Person {

    public static void main(String[] args) {
        Person me = new Person();
        me.describe("Andrew");
        me.describe("Andrew", 21);
    }

    public void describe(String name) {
        System.out.println("My name is " + name);
    }

    public void describe(String name, int age) {
        System.out.println("My name is " + name + " and I am " + age);
    }
}
