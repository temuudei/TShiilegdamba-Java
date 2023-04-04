package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThermostatTest {
    private Thermostat testTheromostat;

    @BeforeEach
    void setUp() {
        testTheromostat = new Thermostat();
        testTheromostat.setTargetTemperature(72);
        testTheromostat.setTolerance(2);
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    public void  thermoStatStateShouldBeOnHeathWhenTemperatureIsLow() {
        //Arrange
        int[] tempInput = {72, 70, 68, 69, 70};
        Thermostat.ThermostatBehavior expected = Thermostat.ThermostatBehavior.ON_HEAT;
        //Act
        Thermostat.ThermostatBehavior actual = testTheromostat.readSensorData(tempInput);
        //Assert
        assertEquals(expected, actual);
    }
    @Test
    public void thermoStatShouldBeOffWhenTemperatureIsWithInTolerance() {
        //Arrange
        int[] tempInput = {72, 70, 69, 71, 70};
        Thermostat.ThermostatBehavior expected = Thermostat.ThermostatBehavior.OFF;
        //Act
        Thermostat.ThermostatBehavior actual = testTheromostat.readSensorData(tempInput);
        //Assert
        assertEquals(expected, actual);
    }
    @Test
    public void thermoStatShouldBeOnAcWhenTemperatureIsHigh() {
        //Arrange
        int[] tempInput = {74, 76, 75, 75, 76};
        Thermostat.ThermostatBehavior expected = Thermostat.ThermostatBehavior.ON_AC;
        //Act
        Thermostat.ThermostatBehavior actual = testTheromostat.readSensorData(tempInput);
        //Assert
        assertEquals(expected, actual);
    }
}