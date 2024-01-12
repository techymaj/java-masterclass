public class Main {
    public static void main(String[] args) {
        SmartKitchen alexa = new SmartKitchen();
        alexa.setKitchenState(true,true,false);
        alexa.doKitchenWork();
    }
}
