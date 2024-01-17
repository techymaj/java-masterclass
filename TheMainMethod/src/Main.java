public class Main {
    public static void main(String[] args) {
        Boy boy = new Boy("Francis", 13);
        boy.describe();

        Boy.create();
    }
}

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void describe() {
        System.out.println(this.name + " is " + this.age + " years old");
    }
}

class Boy extends Person {
    public Boy(String name, int age) {
        super(name, age);
    }

    // You can instantiate objects from any method
    static void create() {
        Boy boy = new Boy("John", 21);
        boy.describe();
    }
}
