package es.upm.miw.devops.code;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DecimalStreamWithFunctionalProgrammingTest {

    private final DecimalStreamWithFunctionalProgramming decimalStream =
            new DecimalStreamWithFunctionalProgramming();

    @Test
    void shouldReturnStreamSize() {
        assertEquals(
                4,
                decimalStream.size(Stream.of(1.0, 2.0, 3.0, 4.0))
        );
    }

    @Test
    void shouldReturnZeroForEmptyStreamSize() {
        assertEquals(
                0,
                decimalStream.size(Stream.empty())
        );
    }

    @Test
    void shouldCalculateSumEvenValues() {
        assertEquals(
                6.0,
                decimalStream.sumEvenValues(
                        Stream.of(1.0, 2.0, 3.0, 4.0, 5.0)
                )
        );
    }

    @Test
    void shouldReturnNaNWhenThereAreNoEvenValues() {
        assertTrue(
                Double.isNaN(
                        decimalStream.sumEvenValues(
                                Stream.of(1.0, 3.0, 5.0)
                        )
                )
        );
    }

    @Test
    void shouldCalculateSum() {
        assertEquals(
                60.0,
                decimalStream.sum(
                        Stream.of(10.0, 20.0, 30.0)
                )
        );
    }

    @Test
    void shouldReturnNaNWhenSumStreamIsEmpty() {
        assertTrue(
                Double.isNaN(
                        decimalStream.sum(Stream.empty())
                )
        );
    }

    @Test
    void shouldCalculateMaximum() {
        assertEquals(
                25.0,
                decimalStream.max(
                        Stream.of(10.0, 25.0, 5.0, 20.0)
                )
        );
    }

    @Test
    void shouldReturnNaNWhenMaximumStreamIsEmpty() {
        assertTrue(
                Double.isNaN(
                        decimalStream.max(Stream.empty())
                )
        );
    }
}