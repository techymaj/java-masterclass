import java.util.List;

public class LowerBounds {

    public void animals(List<? super Animal> animals) {
//        animals.forEach(Animal::eat); // can't call eat() like this because the compiler doesn't know what type of Animal it is and if it has an eat() method
        animals.forEach(animal -> {
            if (animal instanceof Animal) {
                ((Animal) animal).eat();
            }
        }); // this will work because we run the code if and only if the animal is an instance of Animal
        System.out.println("------------------");
        animals.forEach(animal -> {
            if (animal instanceof Dog) {
                ((Dog) animal).bark();
            }
        });
    }
}
