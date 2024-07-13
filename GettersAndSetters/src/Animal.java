import lombok.Getter;
import lombok.Setter;

@Setter
public class Animal {
    int age;
    String name;

    @Override
    public String toString() {
        return String.format("%s is %d", name, age);
    }
}

class Main {

    public static void main(String[] args) {
        Animal dog = new Animal();
        dog.age = 12;
        dog.name = "Rex";
        System.out.println(dog);
    }
}