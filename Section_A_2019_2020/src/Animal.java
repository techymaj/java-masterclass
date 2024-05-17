public class Animal {

    public void locomote() {
        System.out.println("I move rapidly");
    }
}

class Dog extends Animal {
    @Override
    public void locomote() {
        System.out.println("Dogs run");
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.locomote(); // Dogs run

        Animal animal = new Animal();
        animal.locomote(); // I move
    }
}
