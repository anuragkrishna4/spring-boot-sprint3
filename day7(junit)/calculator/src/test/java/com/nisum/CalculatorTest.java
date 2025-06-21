package com.nisum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test void testAdd() {
        assertEquals(5, calculator.add(2, 3));
        assertNotEquals(6, calculator.add(2, 3));
    }

    @Test void testSubtract() {
        assertEquals(1, calculator.subtract(3, 2));
    }

    @Test void testMultiply() {
        assertEquals(6, calculator.multiply(2, 3));
    }

    @Test void testDivide() {
        assertEquals(2, calculator.divide(6, 3));
    }

    @Test void testDivideByZero() {
        Exception e = assertThrows(ArithmeticException.class, () -> calculator.divide(5, 0));
        assertEquals("Division by zero", e.getMessage());
    }
}
