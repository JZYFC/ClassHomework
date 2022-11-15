package io.github.jzyfc.Three;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArraySearchTest {

    @Test
    void searchArray() {
        ArraySearch array = new ArraySearch(new int[] {1, 2, 3, 4, 5});
        assertEquals(4, array.searchArray(5));
    }
}