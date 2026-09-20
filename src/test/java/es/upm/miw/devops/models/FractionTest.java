package es.upm.miw.devops.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FractionTest {

    @Test
    void shouldCreateFractionWithNumeratorAndDenominator() {
        Fraction fraction = new Fraction(2, 3);

        assertEquals(2, fraction.getNumerator());
        assertEquals(3, fraction.getDenominator());
    }

    @Test
    void shouldCreateDefaultFraction() {
        Fraction fraction = new Fraction();

        assertEquals(1, fraction.getNumerator());
        assertEquals(1, fraction.getDenominator());
    }

    @Test
    void shouldSetNumerator() {
        Fraction fraction = new Fraction(2, 3);

        fraction.setNumerator(5);

        assertEquals(5, fraction.getNumerator());
    }

    @Test
    void shouldSetDenominator() {
        Fraction fraction = new Fraction(2, 3);

        fraction.setDenominator(7);

        assertEquals(7, fraction.getDenominator());
    }

    @Test
    void shouldCalculateDecimalValue() {
        Fraction fraction = new Fraction(1, 2);

        assertEquals(0.5, fraction.decimal());
    }

    @Test
    void shouldCalculateDecimalValueWithNegativeNumerator() {
        Fraction fraction = new Fraction(-3, 2);

        assertEquals(-1.5, fraction.decimal());
    }

    @Test
    void shouldReturnFractionAsString() {
        Fraction fraction = new Fraction(2, 3);

        assertEquals(
                "Fraction{numerator=2, denominator=3}",
                fraction.toString()
        );
    }
}