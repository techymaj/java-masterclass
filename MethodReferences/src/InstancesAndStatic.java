public class InstancesAndStatic {

    public static void main(String[] args) {
        InstancesAndStatic instancesAndStatic = new InstancesAndStatic();
        instancesAndStatic.iam();

        // bound method reference
        Runnable iam = instancesAndStatic::iam;
        iam.run();
    }

    private void iam() {
        // instance methods can take static methods
        iamStatic();
    }

    private static void iamStatic() {
        System.out.println("I am static");
    }
}
