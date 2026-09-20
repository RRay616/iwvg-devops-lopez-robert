package es.upm.miw.devops.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointLackOfPOOTest {

    @Test
    void shouldCreatePointWithCoordinates() {
        PointLackOfPOO point = new PointLackOfPOO(3, 4);

        assertEquals(3, point.getX());
        assertEquals(4, point.getY());
    }

    @Test
    void shouldCreatePointAtOrigin() {
        PointLackOfPOO point = new PointLackOfPOO();

        assertEquals(0, point.getX());
        assertEquals(0, point.getY());
    }

    @Test
    void shouldCreatePointWithSameCoordinate() {
        PointLackOfPOO point = new PointLackOfPOO(5);

        assertEquals(5, point.getX());
        assertEquals(5, point.getY());
    }

    @Test
    void shouldCalculateModule() {
        PointLackOfPOO point = new PointLackOfPOO();

        assertEquals(5.0, point.module(3, 4));
    }

    @Test
    void shouldCalculatePhase() {
        PointLackOfPOO point = new PointLackOfPOO();

        assertEquals(Math.PI / 4, point.phase(1, 1), 0.000001);
    }

    @Test
    void shouldTranslateX() {
        PointLackOfPOO point = new PointLackOfPOO(10, 5);

        point.translateXOrigin(3);

        assertEquals(7, point.getX());
        assertEquals(5, point.getY());
    }

    @Test
    void shouldTranslateOrigin() {
        PointLackOfPOO point = new PointLackOfPOO(10, 8);

        point.translateOrigin(3, 2);

        assertEquals(7, point.getX());
        assertEquals(6, point.getY());
    }

    @Test
    void shouldReturnPointAsString() {
        PointLackOfPOO point = new PointLackOfPOO(3, 4);

        assertEquals("Point{x=3, y=4}", point.toString());
    }
}