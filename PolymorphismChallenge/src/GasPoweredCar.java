public class GasPoweredCar extends Car {
    private double avgKmPerLitre;
    private int cylinders;

    public GasPoweredCar(String description, double avgKmPerLitre, int cylinders) {
        super(description);
        this.avgKmPerLitre = avgKmPerLitre;
        this.cylinders = cylinders;
    }

    @Override
    public void startEngine() {
        super.startEngine();
    }

    @Override
    public void drive() {
        super.drive();
    }

    @Override
    protected void runEngine() {
        super.runEngine();
    }

    @Override
    public String toString() {
        return "GasPoweredCar{" +
                "avgKmPerLitre=" + avgKmPerLitre +
                ", cylinders=" + cylinders +
                "} " + super.toString();
    }
}
