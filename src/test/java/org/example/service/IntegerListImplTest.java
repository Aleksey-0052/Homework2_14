package org.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerListImplTest {

    IntegerList integerList = new IntegerListImpl();
    @BeforeEach
    public void setUp() {

        integerList.add(67);
        integerList.add(89);
        integerList.add(54);
        integerList.add(23);
        integerList.add(88);
        integerList.add(5);
        integerList.add(7);

    }

    @Test
    void testContains_true() {

        boolean expected1 = true;
        boolean actual1 = integerList.contains(23);

        boolean expected2 = true;
        boolean actual2 = integerList.contains(88);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);

    }

    @Test
    void testContains_false() {

        boolean expected1 = false;
        boolean actual1 = integerList.contains(33);

        boolean expected2 = false;
        boolean actual2 = integerList.contains(13);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);

    }

}