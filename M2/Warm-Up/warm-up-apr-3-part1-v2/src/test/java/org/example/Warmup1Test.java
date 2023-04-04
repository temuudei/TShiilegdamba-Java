package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Warmup1Test {
    @Test
    void reverseString() {
        //ACT
        String result = Warmup1.reverseString("hi");
        String expected = "ih";
        // ASSERT
        assertEquals(expected, result);
    }
}