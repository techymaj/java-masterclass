public class Shallow implements Cloneable {
    public String name;
    public Shallow(String name) {
        this.name = name;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Shallow sc = new Shallow("Alice");
        Shallow cloned = (Shallow) sc.clone();
        System.out.println(cloned.name);
        sc.setName("Wilfred");
        System.out.println(cloned.name);
        System.out.println(sc.name);
    }
}
