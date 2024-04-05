public class Animal {
    public String name;
    public int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Animal(String name) {
        this(name, 8);
    }

    public Animal() {
        this("Dog", 12);
    }

    public void printDetails() {
        System.out.println(name + " " + age);
    }

    public static void main(String[] args) {
        Animal simba = new Animal("Simba", 2);
        Animal rex = new Animal("Rex");
        Animal dog = new Animal();
        simba.printDetails();
        rex.printDetails();
        dog.printDetails();
    }
}