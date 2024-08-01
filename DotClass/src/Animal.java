public class Animal {

    protected void eat() {
        System.out.println("Animals eat");
    }
}

class Dog extends Animal {

    public Dog(Class<?> classType) {
        if (classType == Dog.class) {
            eat();
        }
        if (classType == Animal.class) {
            super.eat();
        }
    }

    @Override
    public void eat() {
        System.out.println("Dogs eat");
    }

    public static void main(String[] args) {
        new Dog(Dog.class);
        new Dog(Animal.class);
    }
}



