public class LocalClass {
    int age = 29;

    void methodLocal() {
        class M {
            int age;
             M(int age) {
                 this.age = age;
             }

             void desc() {
                 System.out.println("You are " + this.age + " years old");
             }
        }

        M me = new M(30);
        me.desc();
    }
}

class RunMain {
    public static void main(String[] args) {
        LocalClass local = new LocalClass();
        local.methodLocal();
    }
}