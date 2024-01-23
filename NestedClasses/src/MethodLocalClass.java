public class MethodLocalClass {
    final int age = 29;

    void methodLocal(final int aged) {
        class M {
            // this variable is captured
            // meaning: stored with the instance
            final int age;
             M(int age) {
                 this.age = age;
             }

             void desc() {
                 System.out.println("You are " + this.age + " years old");
                 System.out.println("Someone else is " + aged);
                 System.out.println("And another " + MethodLocalClass.this.age);
             }
        }

        M me = new M(30);
        me.desc();
    }
}

class RunMain {
    public static void main(String[] args) {
        MethodLocalClass local = new MethodLocalClass();
        local.methodLocal(33);
    }
}