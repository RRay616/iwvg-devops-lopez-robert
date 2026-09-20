package es.upm.miw.devops.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecimalCollectionTest {

    @Test
    void shouldStartEmpty() {
        DecimalCollection collection = new DecimalCollection();

        assertEquals(0, collection.size());
        assertTrue(Double.isNaN(collection.sum()));
        assertTrue(Double.isNaN(collection.sumEvenValues()));
        assertTrue(Double.isNaN(collection.max()));
    }

    @Test
    void shouldAddValuesAndReturnSize() {
        DecimalCollection collection = new DecimalCollection();

        collection.add(10.0);
        collection.add(20.0);
        collection.add(30.0);

        assertEquals(3, collection.size());
    }

    @Test
    void shouldCalculateSum() {
        DecimalCollection collection = new DecimalCollection();

        collection.add(10.0);
        collection.add(20.0);
        collection.add(30.0);

        assertEquals(60.0, collection.sum());
    }

    @Test
    void shouldCalculateSumWithOneValue() {
        DecimalCollection collection = new DecimalCollection();

        collection.add(15.0);

        assertEquals(15.0, collection.sum());
    }

    @Test
    void shouldReturnNaNWhenThereAreNoEvenValues() {
        DecimalCollection collection = new DecimalCollection();

        collection.add(1.0);
        collection.add(3.0);
        collection.add(5.0);

        assertTrue(Double.isNaN(collection.sumEvenValues()));
    }

    @Test
    void shouldCalculateSumOfEvenValues() {
        DecimalCollection collection = new DecimalCollection();

        collection.add(1.0);
        collection.add(2.0);
        collection.add(3.0);
        collection.add(4.0);
        collection.add(5.0);

        assertEquals(6.0, collection.sumEvenValues());
    }

    @Test
    void shouldCalculateSumOfSingleEvenValue() {
        DecimalCollection collection = new DecimalCollection();

        collection.add(8.0);

        assertEquals(8.0, collection.sumEvenValues());
    }

    @Test
    void shouldCalculateMaximum() {
        DecimalCollection collection = new DecimalCollection();

        collection.add(10.0);
        collection.add(25.0);
        collection.add(5.0);
        collection.add(20.0);

        assertEquals(25.0, collection.max());
    }

    @Test
    void shouldCalculateMaximumWithOneValue() {
        DecimalCollection collection = new DecimalCollection();

        collection.add(42.0);

        assertEquals(42.0, collection.max());
    }

    @Test
    void shouldKeepFirstMaximumWhenFollowingValuesAreSmaller() {
        DecimalCollection collection = new DecimalCollection();

        collection.add(20.0);
        collection.add(10.0);
        collection.add(5.0);

        assertEquals(20.0, collection.max());
    }
}