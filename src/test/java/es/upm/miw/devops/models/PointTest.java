package es.upm.miw.devops.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointTest {

    @Test
    void shouldCreatePointWithCoordinates() {
        Point point = new Point(3, 4);

        assertEquals(3, point.getX());
        assertEquals(4, point.getY());
    }

    @Test
    void shouldCreatePointAtOrigin() {
        Point point = new Point();

        assertEquals(0, point.getX());
        assertEquals(0, point.getY());
    }

    @Test
    void shouldCreatePointWithSameCoordinate() {
        Point point = new Point(5);

        assertEquals(5, point.getX());
        assertEquals(5, point.getY());
    }

    @Test
    void shouldCalculateModule() {
        Point point = new Point(3, 4);

        assertEquals(5.0, point.module());
    }

    @Test
    void shouldCalculatePhase() {
        Point point = new Point(1, 1);

        assertEquals(Math.PI / 4, point.phase(), 0.000001);
    }

    @Test
    void shouldTranslateX() {
        Point point = new Point(10, 5);

        point.translateXOrigin(3);

        assertEquals(7, point.getX());
        assertEquals(5, point.getY());
    }

    @Test
    void shouldTranslateFromOriginPoint() {
        Point point = new Point(10, 8);
        Point origin = new Point(3, 2);

        point.translateOrigin(origin);

        assertEquals(7, point.getX());
        assertEquals(6, point.getY());
    }

    @Test
    void shouldReturnPointAsString() {
        Point point = new Point(3, 4);

        assertEquals("Point{x=3, y=4}", point.toString());
    }
}