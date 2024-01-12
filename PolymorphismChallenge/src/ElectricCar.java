public class ElectricCar extends Car {
    private double avgKmPerCharge;
    private int batterySize;

    public ElectricCar(String description, double avgKmPerCharge, int batterySize) {
        super(description);
        this.avgKmPerCharge = avgKmPerCharge;
        this.batterySize = batterySize;
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
        return "ElectricCar{" +
                "avgKmPerCharge=" + avgKmPerCharge +
                ", batterySize=" + batterySize +
                "} " + super.toString();
    }
}
