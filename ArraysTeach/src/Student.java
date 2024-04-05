public class Student {
    String name;
    double cgpa;

    public Student(String name, double cgpa) {
        this.name = name;
        this.cgpa = cgpa;
    }

    public void describe() {
        System.out.println("Name is " + name + "cgpa is " + cgpa);
    }

    private void privateInfo() {
        System.out.println("Private");
    }

    public static void main(String[] args) {
        Student remy = new Student("Remy", 4.5);
        remy.describe();
        remy.privateInfo();
    }
}

