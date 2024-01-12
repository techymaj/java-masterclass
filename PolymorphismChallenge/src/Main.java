public class Main {
    public static void main(String[] args) {
        ElectricCar tesla = new ElectricCar("Tesla", 400,34);
        GasPoweredCar gp = new GasPoweredCar("Toyota", 20,4);
        HybridCar hybrid = new HybridCar("Toureg", 500,23,8);
        gp.startEngine();
        hybrid.startEngine();
        tesla.startEngine();
    }
}
