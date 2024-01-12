public class Car {
    private String description;

    public Car(String description) {
        this.description = description;
    }

    public void startEngine() {
        System.out.println("Type: " + this.description);
        System.out.println(getClass().getSimpleName());
        System.out.println("Engine started");
        System.out.println("*".repeat(100));
    }

    public void drive() {
        System.out.println("Car is in drive");
        runEngine();
    }

    protected void runEngine() {
        System.out.println("Engine is running..");
    }

    @Override
    public String toString() {
        return "Car{" +
                "description='" + description + '\'' +
                '}';
    }
}
