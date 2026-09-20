package es.upm.miw.devops.code;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FlowTest {

    private final Flow flow = new Flow();

    @Test
    void shouldCreateStreamFromList() {
        assertEquals(
                List.of("0", "1", "2"),
                flow.streamFromList().toList()
        );
    }

    @Test
    void shouldCreateStreamFromRange() {
        assertEquals(
                List.of(0, 1, 2, 3),
                flow.streamFromRange().boxed().toList()
        );
    }

    @Test
    void shouldCreateStreamFromOf() {
        assertEquals(
                List.of("0", "1", "2"),
                flow.streamFromOf().toList()
        );
    }

    @Test
    void shouldGenerateLimitedStream() {
        assertEquals(
                5,
                flow.streamFromGenerate(5).count()
        );
    }

    @Test
    void shouldGenerateEmptyStreamWhenLimitIsZero() {
        assertEquals(
                0,
                flow.streamFromGenerate(0).count()
        );
    }

    @Test
    void shouldCreateIterativeStream() {
        assertEquals(
                List.of(0, 2, 4, 6),
                flow.streamFromIterate(4).toList()
        );
    }

    @Test
    void shouldCreateEmptyIterativeStreamWhenLimitIsZero() {
        assertTrue(
                flow.streamFromIterate(0).toList().isEmpty()
        );
    }

    @Test
    void shouldConvertStreamToList() {
        assertEquals(
                List.of(1, 2, 3),
                flow.toList(Stream.of(1, 2, 3))
        );
    }

    @Test
    void shouldConvertStreamToArray() {
        assertArrayEquals(
                new Integer[]{1, 2, 3},
                flow.toArray(Stream.of(1, 2, 3))
        );
    }

    @Test
    void shouldFilterPositiveValues() {
        assertEquals(
                List.of(0, 2, 4),
                flow.filterPositives(Stream.of(-2, 0, 2, 4)).toList()
        );
    }

    @Test
    void shouldSkipFirstValue() {
        assertEquals(
                List.of(2, 3),
                flow.skipFirst(Stream.of(1, 2, 3)).toList()
        );
    }

    @Test
    void shouldReturnStreamSize() {
        assertEquals(
                4,
                flow.size(Stream.of(1, 2, 3, 4))
        );
    }

    @Test
    void shouldRemoveDuplicates() {
        assertEquals(
                List.of(1, 2, 3),
                flow.removeCopy(Stream.of(1, 2, 1, 3, 2)).toList()
        );
    }

    @Test
    void shouldDebugStream() {
        assertDoesNotThrow(
                () -> flow.debug(Stream.of("one", "two", "three"))
        );
    }

    @Test
    void shouldMapValuesToString() {
        assertEquals(
                List.of("1", "2", "3"),
                flow.mapToString(Stream.of(1, 2, 3)).toList()
        );
    }

    @Test
    void shouldIncrementValues() {
        assertEquals(
                List.of(2, 3, 4),
                flow.increment(Stream.of(1, 2, 3)).toList()
        );
    }

    @Test
    void shouldFlattenArrays() {
        assertEquals(
                List.of(1, 2, 3, 4),
                flow.flatten(Stream.of(
                        new Integer[]{1, 2},
                        new Integer[]{3, 4}
                )).toList()
        );
    }

    @Test
    void shouldCalculateSum() {
        assertEquals(
                10.0,
                flow.sum(Stream.of(1, 2, 3, 4))
        );
    }

    @Test
    void shouldThrowExceptionWhenCalculatingSumOfEmptyStream() {
        assertThrows(
                ArithmeticException.class,
                () -> flow.sum(Stream.empty())
        );
    }

    @Test
    void shouldCalculateMaximum() {
        assertEquals(
                8.0,
                flow.max(Stream.of(3, 8, 2, 5))
        );
    }

    @Test
    void shouldThrowExceptionWhenCalculatingMaximumOfEmptyStream() {
        assertThrows(
                ArithmeticException.class,
                () -> flow.max(Stream.empty())
        );
    }

    @Test
    void shouldMultiplyValues() {
        assertEquals(
                24.0,
                flow.mul(Stream.of(2, 3, 4))
        );
    }

    @Test
    void shouldReturnOneWhenMultiplyingEmptyStream() {
        assertEquals(
                1.0,
                flow.mul(Stream.empty())
        );
    }

    @Test
    void shouldIncrementAndSumValues() {
        assertEquals(
                9.0,
                flow.incrementAndSum(Stream.of(1, 2, 3))
        );
    }

    @Test
    void shouldThrowExceptionWhenIncrementingAndSummingEmptyStream() {
        assertThrows(
                ArithmeticException.class,
                () -> flow.incrementAndSum(Stream.empty())
        );
    }

    @Test
    void shouldProcessTerminalStream() {
        assertDoesNotThrow(
                () -> flow.terminal(Stream.of(1, 2, 3))
        );
    }

    @Test
    void shouldFindValueInStream() {
        assertTrue(
                flow.isValueContent(Stream.of(1, 2, 3), 2)
        );
    }

    @Test
    void shouldReturnFalseWhenValueIsNotInStream() {
        assertFalse(
                flow.isValueContent(Stream.of(1, 2, 3), 5)
        );
    }

    @Test
    void shouldReturnTrueWhenAllValuesArePositive() {
        assertTrue(
                flow.areAllPositive(Stream.of(0, 2, 5))
        );
    }

    @Test
    void shouldReturnFalseWhenNotAllValuesArePositive() {
        assertFalse(
                flow.areAllPositive(Stream.of(-1, 0, 2))
        );
    }
}