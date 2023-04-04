package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ThermometerTest {
    private Thermometer thermometerTest;
    @BeforeEach
    void setUp() {
        thermometerTest = new Thermometer();
    }

    @Test
    void celsiusToFahrenheitTest() {
        //ACT
        double actual = thermometerTest.celsiusToFahrenheit(12.45);
        double expected = 54.41;
        //ASSERT    
        assertEquals(expected, actual);
    }

    @Test
    void fahreinheitToCelsiusTest() {
        //ACT
        double actual = Math.round(thermometerTest.fahreinheitToCelsius(54.41) * 100.0) / 100.0;
        double expected = 12.45;
        //ASSERT
        assertEquals(expected, actual);
    }

    @Test
    void celsiusToKelvinTest() {
        //ACT
        double actual = Math.round(thermometerTest.celsiusToKelvin(12.45) * 100.0) / 100.0;
        double expected = 285.6;
        //ASSERT
        assertEquals(expected, actual);
    }

    @Test
    void kelvinToCelsiusTest() {
        //ACT
        double actual = Math.round(thermometerTest.kelvinToCelsius(285.6) * 100.0) / 100.0;
        double expected = 12.45;
        //ASSERT
        assertEquals(expected, actual);
    }

    @Test
    void fahrenheitToKelvinTest() {
        //ACT
        double actual = Math.round(thermometerTest.fahrenheitToKelvin(45.14) * 100.0) / 100.0;
        double expected = 280.45;
        //ASSERT
        assertEquals(expected, actual);
    }

    @Test
    void kelvinToFahrenheitTest() {
        //ACT
        double actual = Math.round(thermometerTest.kelvinToFahrenheit(280.45) * 100.0) / 100.0;
        double expected = 45.14;
        //ASSERT
        assertEquals(expected, actual);
    }
}