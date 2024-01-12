public class Animal extends Object implements Cloneable{

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    private String name;
    public Animal(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) throws CloneNotSupportedException{
        Dog dog =  new Dog("Dog");
        dog.setName("Alex");
        Dog clone = (Dog) dog.clone();
        clone.setName("Ziggy");
        System.out.println(dog.getName());
        System.out.println(clone.getName());
    }
}
