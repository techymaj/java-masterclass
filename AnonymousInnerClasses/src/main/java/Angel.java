public class Angel {

    public static void main(String[] args) {
        God god = new God() {
            @Override
            public void doGodlyStuff() {
                System.out.println("-");
            }

            @Override
            public void doGodlyStuff2() {
                System.out.println("-");
            }
        };
        god.doGodlyStuff();
    }
}
