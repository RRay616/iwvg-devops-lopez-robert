package es.upm.miw.devops.code;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ParallelStreamTest {

    private final ParallelStream parallelStream = new ParallelStream();

    @Test
    void shouldExecuteSequentialRange() {
        assertDoesNotThrow(() -> invokePrivate("sequentialRange", 10));
    }

    @Test
    void shouldExecuteSequentialRangeWithEmptyRange() {
        assertDoesNotThrow(() -> invokePrivate("sequentialRange", 0));
    }

    @Test
    void shouldExecuteSequentialCollection() {
        assertDoesNotThrow(() -> invokePrivate("sequentialCollection", 10));
    }

    @Test
    void shouldExecuteSequentialCollectionWithEmptyCollection() {
        assertDoesNotThrow(() -> invokePrivate("sequentialCollection", 0));
    }

    @Test
    void shouldExecuteParallelRange() {
        assertDoesNotThrow(() -> invokePrivate("parallelRange", 10));
    }

    @Test
    void shouldExecuteParallelRangeWithEmptyRange() {
        assertDoesNotThrow(() -> invokePrivate("parallelRange", 0));
    }

    @Test
    void shouldExecuteParallelIterative() {
        assertDoesNotThrow(() -> invokePrivate("parallelIterative", 10));
    }

    @Test
    void shouldExecuteParallelIterativeWithEmptyRange() {
        assertDoesNotThrow(() -> invokePrivate("parallelIterative", 0));
    }

    @Test
    void shouldExecuteParallelArray() {
        assertDoesNotThrow(() -> invokePrivate("parallelArray", 10));
    }

    @Test
    void shouldExecuteParallelArrayWithEmptyArray() {
        assertDoesNotThrow(() -> invokePrivate("parallelArray", 0));
    }

    private void invokePrivate(String methodName, int value)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = ParallelStream.class.getDeclaredMethod(methodName, int.class);
        method.setAccessible(true);
        method.invoke(parallelStream, value);
    }
}