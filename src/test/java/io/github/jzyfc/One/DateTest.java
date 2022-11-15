package io.github.jzyfc.One;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void showDate() {
        Date d1 = new Date();
        d1.showDate();
    }

    @Test
    void setDate() {
        InputStream sysIn = System.in;
        ByteArrayInputStream customIn = new ByteArrayInputStream("2022\n9\n31".getBytes());
        System.setIn(customIn);

        Date d1 = new Date();
        d1.showDate();
        d1.setDate();
        d1.showDate();

        System.setIn(sysIn);
    }
}