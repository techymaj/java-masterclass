public class Animal {

    public void eat() {
        System.out.println("Animals eat");
    }
}

// This is inheritance (Is-A relationship)
// A Human Is-A Animal
class Human extends Animal {
    @Override
    public void eat() {
        // super.eat() calls the method in the parent class which is Animal
        // you can choose to not call super.eat()
        super.eat(); // This calls the eat() method in the Animal class
        System.out.println("Humans eat");
    }

    public static void main(String[] args) {
        Human human = new Human();
//        human.eat(); // This calls the eat() method in the Human class
        super.eat();
    }
}