public class Main {
    public static void main(String[] args) {
        POJO me = new POJO("Jax",
                "black",
                2,
                "Brown",
                4,
                30,
                174,
                29,
                new String[]{"English", "Kiswahili", "Luganda", "French"});

        System.out.println(me);

        Record record = new Record("Jax",
                "brown",
                2,
                "Brown",
                4,
                30,
                174,
                29,
                new String[]{"English", "Kiswahili", "Luganda", "French"});
        System.out.println(record);
    }
}
