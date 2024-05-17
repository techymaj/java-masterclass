public class Main {

    public static void main(String[] args) {

        Anon anon = new Anon();
        anon.eat();

        Anon anonymous = new Anon(){
            @Override
            public void eat() {
                System.out.println("Ate");
            }
        };
        anonymous.eat();
    }
}
