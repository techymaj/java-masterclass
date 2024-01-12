public class SmartKitchen {
    private CoffeeMaker brewMaster;
    private DishWasher dishWasher;
    private Refrigerator iceBox;

    public SmartKitchen() {
        this.brewMaster = new CoffeeMaker();
        this.dishWasher = new DishWasher();
        this.iceBox = new Refrigerator();
    }

    public void addWater() {
        this.brewMaster.brewCoffee();
    }

    public void pourMilk() {
        this.iceBox.orderFood();
    }

    public void loadDishWasher() {
        this.dishWasher.doDishes();
    }

    public void setKitchenState(boolean addWater, boolean pourMilk, boolean loadDishWasher) {
        this.brewMaster.setHasWorkToDo(addWater);
        this.iceBox.setHasWorkToDo(pourMilk);
        this.dishWasher.setHasWorkToDo(loadDishWasher);
    }
    public void doKitchenWork() {
        addWater();
        pourMilk();
        loadDishWasher();
    }
}
