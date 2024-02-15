public class Main implements LambdaMe {

    public static void main(String[] args) {
        LambdaMe lambda = lambdaString -> {
            System.out.println(lambdaString.toUpperCase() + " Marvel Jesus");
        };
        lambda.printer("I am");
    }

    @Override
    public void printer(String s) {
        System.out.println(s);
    }
}
