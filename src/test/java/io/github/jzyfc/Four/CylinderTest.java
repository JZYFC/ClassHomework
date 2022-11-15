package io.github.jzyfc.Four;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {
    @Test
    void cylinderTest() {
        Cylinder cylinder = new Cylinder(2., 2.);
        System.out.println(cylinder.area());
        System.out.println(cylinder.color);
        System.out.println(cylinder.volume());
    }
}