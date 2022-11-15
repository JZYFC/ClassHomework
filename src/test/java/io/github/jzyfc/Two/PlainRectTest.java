package io.github.jzyfc.Two;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlainRectTest {

    private final PlainRect plainRect = new PlainRect(10, 10, 20, 10);

    @Test
    void area() {
        assertEquals(200., plainRect.area());
    }

    @Test
    void perimeter() {
        assertEquals(60., plainRect.perimeter());
    }

    @Test
    void isInside() {
        assertTrue(plainRect.isInside(25.5, 13));
    }
}