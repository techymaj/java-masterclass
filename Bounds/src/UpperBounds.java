import java.util.List;

public class UpperBounds {

    public void animals(List<? extends Animal> animals) {
        animals.forEach(Animal::eat);
        System.out.println("------------------");
        animals.forEach(animal -> {
            if (animal instanceof Dog) {
                ((Dog) animal).bark();
            }
        });
    }
}

