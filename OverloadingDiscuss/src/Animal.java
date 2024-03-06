public class Animal {

    public void eat() {
        System.out.println("Animals eat");
    }
}


class Dog extends Animal {

    @Override
    public String eat() {
        System.out.println("Ate");
        return null;
    }
}