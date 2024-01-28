import java.util.function.BiFunction;

public enum OperationLambda implements IOperation {

    ADD(Integer::sum, '+'),
    SUBTRACT((a, b) -> a - b, '-'),
    MULTIPLY((a, b) -> a * b, '*'),
    DIVIDE((a, b) -> a / b, '/');

    private final BiFunction<Integer, Integer, Integer> operation;
    private final char sign;

    OperationLambda(BiFunction<Integer, Integer, Integer> operation, char sign) {
        this.operation = operation;
        this.sign = sign;
    }

    public int operands(int a, int b) {
        System.out.printf("(%d %s %d) = ", a, this.sign, b);
        return this.operation.apply(a, b);
    }

    public static int ADD(int a, int b) {
        return a + b;
    }
}

interface IOperation {
    default char sign(char sign) {
        System.out.print("That's a: ");
        return sign;
    }
}
