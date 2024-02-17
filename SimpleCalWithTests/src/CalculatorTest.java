import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    @Test
    @DisplayName("Simple addition should work")
    void testAdd() {
        assertEquals(5, Calculator.add(2, 3));
    }

    @Test
    @DisplayName("Simple subtraction should work")
    void testSubtract() {
        assertEquals(5, Calculator.subtract(8, 3));
    }

    @Test
    @DisplayName("Simple multiplication should work")
    void testMultiply() {
        assertEquals(6, Calculator.multiply(2, 3));
    }

    @Test
    @DisplayName("Simple division should work")
    void testDivide() {
        assertEquals(2, Calculator.divide(4, 2));
    }

    @Test
    @DisplayName("Divide by Zero should throw an exception")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> {
            Calculator.divide(4, 0);
        });
    }

    @Test
    @DisplayName("Simple modulo should work")
    void testModulo() {
        assertEquals(1, Calculator.modulo(10, 3));
    }

    @Test
    @DisplayName("Simple square should work")
    void testSquare() {
        assertEquals(25, Calculator.square(5));
    }

    @Test
    @DisplayName("Simple cube should work")
    void testCube() {
        assertEquals(125, Calculator.cube(5));
    }

    @Test
    @DisplayName("Simple power should work")
    void testPower() {
        assertEquals(125, Calculator.power(5, 3));
    }

    @Test
    @DisplayName("Simple factorial should work")
    void testFactorial() {
        assertEquals(120, Calculator.factorial(5));
    }

    @Test
    @DisplayName("Simple absolute should work")
    void testAbsolute() {
        assertEquals(5, Calculator.absolute(-5));
    }

    @Test
    @DisplayName("Simple maximum should work")
    void testMaximum() {
        assertEquals(5, Calculator.maximum(5, 3));
    }

    @Test
    @DisplayName("Simple minimum should work")
    void testMinimum() {
        assertEquals(3, Calculator.minimum(5, 3));
    }

    @Test
    @DisplayName("System output for add")
    void testSystemOutputForAdd() {
        // Redirect System.out to capture the output
        var outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Call the method that prints to console
        System.out.println("Addition: " + Calculator.add(5, 3));

        // Convert the captured output to a string
        String printedOutput = outputStreamCaptor.toString().trim();

        // Assert the content of the printed output
        assertEquals("Addition: 8", printedOutput);
    }
}
