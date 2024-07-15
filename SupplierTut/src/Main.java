import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Supplier<Animal> dog = Animal::new;
        var rex = dog.get();
        System.out.printf("%s is %d%n", rex.name, rex.age);

        Supplier<Animal> dog2 = () -> new Animal(2, "Simba");
        var simba = dog2.get();
        System.out.printf("%s is %d%n", simba.name, simba.age);

        Supplier<Animal> dog3 = () -> new Animal(20);
        var mufasa = dog3.get();
        System.out.printf("%s is %d%n", mufasa.name, mufasa.age);
    }
}

class Animal {
    int age;
    String name;

    public Animal(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Animal(int age) {
        this(age, "Mufasa");
    }

    public Animal() {
        this(4, "Rex");
    }
}