package tech.majaliwa;

import tech.majaliwa.METADATA.ImportantString;
import tech.majaliwa.METADATA.VeryImportant;

@VeryImportant
public class Cat {
    @ImportantString
    String name;

    public Cat(String name) {
        this.name = name;
    }

    @Deprecated
    public void jump() {
        System.out.println("Jump!");
    }

    @VeryImportant
    public void sleep() {
        System.out.println("Sleeping...");
    }

    @Override
    public String toString() {
        return name;
    }
}
