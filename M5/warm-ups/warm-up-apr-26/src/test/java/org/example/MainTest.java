package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void arrayDifference() {
        //ARRANGE
        int[] numbers = {4,5,3,2,8};
        int expected = 6;
        // ACT/ASSERT
        assertEquals(expected, Main.arrayDifference(numbers));
    }
}