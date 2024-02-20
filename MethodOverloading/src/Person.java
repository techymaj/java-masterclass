public class Person {

    // Create a method that takes in a name and prints it
    public void describe(String name) {
        System.out.println("My name is " + name);
    }

    // Create a method that takes in a name and age and prints it
    public String describe(String name, int age) {
        return "My name is " + name + " and I am " + age;
    }

    public static void main(String[] args) {
        // Create an instance of the Person class
        Person me = new Person();
        // Call the describe method with a name
        me.describe("Andrew");
        // Call the describe method with a name and age
        String iAm = me.describe("Andrew", 21);
        System.out.println(iAm);
    }
}
