public class Interning {
    public String fName = "John";
    public String middleName = new String("John").intern();
    public String lastName = "Deogratious";

    public static void main(String[] args) {
       Interning i = new Interning();
       i.interned();
    }
    
    public void interned() {
        System.out.println(this.fName == this.middleName);
        System.out.println(this.fName.equals(this.middleName));
        System.out.println(this.fName == this.lastName);
        System.out.println(this.fName.equals(this.lastName));
    }
}
