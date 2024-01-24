package tech.majaliwa;

import tech.majaliwa.METADATA.RunImmediately;

public class Dog {

    @RunImmediately(exception = Dog.class)
    public void bark() {
        System.out.println("Bark!");
    }

    @RunImmediately(times = 1)
    public void sleep() {
        System.out.println("Sleeping...");
    }

    public void eat() {
        System.out.println("Eating...");
    }

    @Override
    public String toString() {
        return "Dog";
    }
}
