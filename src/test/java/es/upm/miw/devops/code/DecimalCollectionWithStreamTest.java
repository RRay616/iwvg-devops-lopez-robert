package es.upm.miw.devops.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecimalCollectionWithStreamTest {

    @Test
    void shouldStartEmpty() {
        DecimalCollectionWithStream collection = new DecimalCollectionWithStream();

        assertEquals(0, collection.size());
        assertTrue(Double.isNaN(collection.sum()));
        assertTrue(Double.isNaN(collection.sumEvenValues()));
        assertTrue(Double.isNaN(collection.max()));
    }

    @Test
    void shouldAddValuesAndReturnSize() {
        DecimalCollectionWithStream collection = new DecimalCollectionWithStream();

        collection.add(10.0);
        collection.add(20.0);
        collection.add(30.0);

        assertEquals(3, collection.size());
    }

    @Test
    void shouldCalculateSum() {
        DecimalCollectionWithStream collection = new DecimalCollectionWithStream();

        collection.add(10.0);
        collection.add(20.0);
        collection.add(30.0);

        assertEquals(60.0, collection.sum());
    }

    @Test
    void shouldCalculateSumWithOneValue() {
        DecimalCollectionWithStream collection = new DecimalCollectionWithStream();

        collection.add(15.0);

        assertEquals(15.0, collection.sum());
    }

    @Test
    void shouldReturnNaNWhenThereAreNoEvenValues() {
        DecimalCollectionWithStream collection = new DecimalCollectionWithStream();

        collection.add(1.0);
        collection.add(3.0);
        collection.add(5.0);

        assertTrue(Double.isNaN(collection.sumEvenValues()));
    }

    @Test
    void shouldCalculateSumOfEvenValues() {
        DecimalCollectionWithStream collection = new DecimalCollectionWithStream();

        collection.add(1.0);
        collection.add(2.0);
        collection.add(3.0);
        collection.add(4.0);
        collection.add(5.0);

        assertEquals(6.0, collection.sumEvenValues());
    }

    @Test
    void shouldCalculateMaximum() {
        DecimalCollectionWithStream collection = new DecimalCollectionWithStream();

        collection.add(10.0);
        collection.add(25.0);
        collection.add(5.0);
        collection.add(20.0);

        assertEquals(25.0, collection.max());
    }

    @Test
    void shouldCalculateMaximumWithOneValue() {
        DecimalCollectionWithStream collection = new DecimalCollectionWithStream();

        collection.add(42.0);

        assertEquals(42.0, collection.max());
    }
}