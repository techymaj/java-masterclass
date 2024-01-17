public class Main implements Skills {
    final int ages = 24;
    public static final String name = "Poe";


    public static void main(String[] args) {
        Thoughts thoughts = new Main();
        thoughts.goodThoughts();
        System.out.println(Thoughts.MY_CONSTANT);

        System.out.println(Main.name);
        Main main = new Main();
        System.out.println(main.ages);
        Thoughts.badThoughts();
        thoughts.goodOrBad(); // also calls the private method implemented
        Skills.enumerate();
    }
    @Override
    public boolean knowJava() {
        return false;
    }

    @Override
    public boolean knowDart() {
        return false;
    }

    @Override
    public boolean knowCPlusPlus() {
        return false;
    }

    @Override
    public boolean hasAssets() {
        return false;
    }

    @Override
    public void goodThoughts() {
        System.out.println("Thinking...");
    }

    @Override
    public void goodOrBad() {
        System.out.println("-".repeat(50));
        System.out.println("Override");
        Skills.super.goodOrBad();
    }
}
