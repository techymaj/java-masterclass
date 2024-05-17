package visibility;

public class Visibility {
    String name;

    public void nameMe() {
        System.out.println(this.name);
    }

    public static void main(String[] args) {
        Visibility visibility = new Visibility();
        visibility.nameMe();
    }
}

class SubClass extends Visibility {

    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        System.out.println(subClass.name);
    }
}
