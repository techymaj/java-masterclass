public abstract class God {
    public abstract void doGodlyStuff();
}
class Angel extends God {
    @Override
    public void doGodlyStuff() {
        System.out.println("I am an angel, I am here to protect you!");
    }
    public static void main(String[] args) {
        God angel = new Angel();
        angel.doGodlyStuff();

        God god = new God() {
            @Override
            public void doGodlyStuff() {

            }
        };
    }
}