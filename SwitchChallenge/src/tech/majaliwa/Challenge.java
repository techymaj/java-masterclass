package tech.majaliwa;

public class Challenge {
    public static void main(String[] args) {

        char switchVariable = 'x';

        switch (switchVariable) {
            case 'A':
                System.out.println(switchVariable + " is Able");
                break;
            case 'B':
                System.out.println(switchVariable + " is Baker");
                break;
            case 'C':
                System.out.println(switchVariable + " is Charlie");
                break;
            case 'D':
                System.out.println(switchVariable + " is Dog");
                break;
            case 'E':
                System.out.println(switchVariable + " is Easy");
                break;
            default:
                System.out.println(switchVariable + " is Undefined");
                break;
        }

    }
}
