public class Calculator {

    public static void main(String[] args) {

        System.out.println(OperationLambda.ADD.operands(1, 2));
        System.out.println(OperationLambda.SUBTRACT.operands(1, 2));
        System.out.println(OperationLambda.MULTIPLY.operands(1, 2));
        System.out.println(OperationLambda.DIVIDE.operands(1, 2));
        System.out.println(OperationLambda.ADD(1, 2));
        System.out.println(OperationLambda.ADD.sign('+'));
        System.out.println(OperationLambda.SUBTRACT.sign('-'));
        System.out.println(OperationLambda.MULTIPLY.sign('*'));
        System.out.println(OperationLambda.DIVIDE.sign('/'));
    }
}
