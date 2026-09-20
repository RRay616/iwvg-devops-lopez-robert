package es.upm.miw.devops.code;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class LambdaTest {

    @Test
    void shouldLogInfo() {
        assertDoesNotThrow(
                () -> Lambda.logInfo.accept("Hello")
        );
    }

    @Test
    void shouldLogInfoDetail() {
        assertDoesNotThrow(
                () -> Lambda.logInfoDetail.accept("Hello")
        );
    }

    @Test
    void shouldConvertStringToInteger() {
        assertEquals(
                42,
                Lambda.convertToInt.apply("42")
        );
    }

    @Test
    void shouldConvertStringToIntegerAndAddOne() {
        assertEquals(
                43,
                Lambda.convertToIntPlus1.apply("42")
        );
    }

    @Test
    void shouldConvertZeroToOne() {
        assertEquals(
                1,
                Lambda.convertToIntZeroTo1.apply("0")
        );
    }

    @Test
    void shouldKeepNonZeroValueUnchanged() {
        assertEquals(
                42,
                Lambda.convertToIntZeroTo1.apply("42")
        );
    }

    @Test
    void shouldReturnTrueWhenValueEqualsTwo() {
        assertTrue(
                Lambda.equalsTo2.test("two")
        );
    }

    @Test
    void shouldReturnFalseWhenValueDoesNotEqualTwo() {
        assertFalse(
                Lambda.equalsTo2.test("three")
        );
    }

    @Test
    void shouldGenerateDots() {
        assertEquals(
                "...",
                Lambda.generateDots.get()
        );
    }

    @Test
    void shouldLogTwoMessages() {
        assertDoesNotThrow(
                () -> Lambda.biLogInfo.accept("Hello", "World")
        );
    }

    @Test
    void shouldMultiplyTwoNumbers() {
        assertEquals(
                42,
                Lambda.multiply.apply(6, 7)
        );
    }

    @Test
    void shouldCompareTwoEqualStrings() {
        assertTrue(
                Lambda.compare.test("hello", "hello")
        );
    }

    @Test
    void shouldCompareTwoDifferentStrings() {
        assertFalse(
                Lambda.compare.test("hello", "world")
        );
    }

    @Test
    void shouldThrowExceptionWhenUtilityClassConstructorIsInvoked() throws Exception {
        Constructor<Lambda> constructor =
                Lambda.class.getDeclaredConstructor();

        constructor.setAccessible(true);

        InvocationTargetException exception = assertThrows(
                InvocationTargetException.class,
                constructor::newInstance
        );

        assertInstanceOf(
                IllegalStateException.class,
                exception.getCause()
        );
    }
}