package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    Calculator calc = new Calculator();

    @Test
    void testAdd() throws InvalidInputException {
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    void testSubtract() throws InvalidInputException {
        assertEquals(-1, calc.subtract(2, 3));
    }

    @Test
    void testMultiply() throws InvalidInputException {
        assertEquals(6, calc.multiply(2, 3));
    }

    @Test
    void testDivide() throws InvalidInputException {
        assertEquals(2, calc.divide(6, 3));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(5, 0));
    }

    @Test
    void testSqrtNegative() {
        assertThrows(InvalidInputException.class, () -> calc.sqrt(-4));
    }

    @Test
    void testNaN() {
        assertThrows(InvalidInputException.class, () -> calc.add(Double.NaN, 1));
    }

    @Test
    void testInfinity() {
        assertThrows(InvalidInputException.class,
                () -> calc.multiply(Double.POSITIVE_INFINITY, 2));
    }

    @Test
    void testStringInsteadOfNumber() {
        assertThrows(NumberFormatException.class, () -> Double.parseDouble("abc"));
    }
}
