public class Main implements Skills {

    public static void main(String[] args) {
        Thoughts thoughts = new Main();
        thoughts.goodThoughts();
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
}
