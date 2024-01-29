import java.util.Comparator;

public class JustCompare implements Comparator<Integer> {

    public static void main(String[] args) {
        Integer a = 20;
        Integer b = 20;

        Comparator<Integer> justCompare = new JustCompare();

        int result = justCompare.compare(a, b);
        System.out.println(result);

    }

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Student jax = new Student("Jax");
        Student jj = new Student("JJ");
        // no need to implement Comparator interface
        Comparator<Student> compareByName = Comparator.comparing(o -> o.name);
        int result = compareByName.compare(jax, jj);
        System.out.println(result);

    }
}