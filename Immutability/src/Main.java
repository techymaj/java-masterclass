public class Main extends Immutable {
    public static void main(String[] args) {
        Immutable me = new Immutable();
//        me.name = "John";
        me.age = 21;
                            
        me.describe();
    }
}

class Immutable {
    public final String name = "Wilfried";
    public int age;

    public void setAge(int age) {
        this.age = age;
    }

    public void describe() {
        System.out.println("Your name is " + this.name + " and you are " + this.age + " years old");
    }
}