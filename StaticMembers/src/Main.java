public class Main {
    static String genus;

    public Main(String genus) {
        this.genus = genus; // not the correct way, use Main.genus instead
    }

    public String getGenus() {
        return genus;
    }

    public static void main(String[] args) {
        Main main = new Main("Canis");
        Main m = new Main("Dear");

        String g = main.getGenus();
        System.out.println(g);
    }

}