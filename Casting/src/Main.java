class Animal {
    String name;

    public static void main(String[] args) {
        Animal me = new Animal();
        me.name = "Roar";
//        System.out.println(me.name);
    }
}

class Man extends Animal{
    public static void main(String[] args) {
        // Read from right to left: Man is an Animal. Implicit casting
        Animal jax = new Man();
        jax.name = "Jax";
//        System.out.println(jax.name);
    }
}



class Father extends Family {
    public void fatherlyDuties() {
        System.out.println("Raise son");
    }
}
class Son extends Family {
    public void sonDuties() {
        System.out.println("Listen to father");
    }
}
class Family {
    public static void main(String[] args) {
        // Implicit casting. Upcasting
        Family me = new Son();
        me.isBlood(me);
    }
    public void isBlood(Family member) {
        System.out.println("You are blood");
        if (member instanceof Son) {
            // Downcast member to Son
            ((Son) member).sonDuties();
        } else if (member instanceof Father) {
            // Downcast member to Father
            ((Father) member).fatherlyDuties();
        }
    }
}