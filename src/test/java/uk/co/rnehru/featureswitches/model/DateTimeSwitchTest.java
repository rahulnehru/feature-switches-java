package uk.co.rnehru.featureswitches.model;

import org.junit.jupiter.api.Test;
import uk.co.rnehru.featureswitches.model.DateTimeSwitch;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DateTimeSwitchTest {

    private final ZonedDateTime timeInPast = ZonedDateTime.now().minusYears(1);
    private final ZonedDateTime timeInFuture = ZonedDateTime.now().plusYears(1);

    @Test
    void isOnReturnsTrueIfSwitchIsOn() {
        DateTimeSwitch dateTimeSwitch = new DateTimeSwitch(timeInPast);
        assertTrue(dateTimeSwitch.isOn());
    }

    @Test
    void isOnReturnsFalseIfSwitchIsOff() {
        DateTimeSwitch dateTimeSwitch = new DateTimeSwitch(timeInFuture);
        assertFalse(dateTimeSwitch.isOn());
    }

    @Test
    void turnOnWillChangeSwitchToBeOn() {
        DateTimeSwitch dateTimeSwitch = new DateTimeSwitch(timeInFuture);
        dateTimeSwitch.turnOn();
        assertTrue(dateTimeSwitch.isOn());
    }

    @Test
    void turnOffWillChangeSwitchToBeOff() {
        DateTimeSwitch dateTimeSwitch = new DateTimeSwitch(timeInPast);
        dateTimeSwitch.turnOff();
        assertFalse(dateTimeSwitch.isOn());
    }

    @Test
    void getActivationDateTimeReturnsZonedDateTimeOfSwitch() {
        ZonedDateTime firstOfJan2020 = ZonedDateTime.of(LocalDate.of(2020, 1, 1), LocalTime.ofSecondOfDay(0), ZoneId.of("UTC"));
        DateTimeSwitch dateTimeSwitch = new DateTimeSwitch(firstOfJan2020);
        ZonedDateTime actual = dateTimeSwitch.getActivationDateTime();
        assertEquals(firstOfJan2020, actual);
    }

    @Test
    void resetShouldSetBackToInitialStateWhenInitialStateWasOff() {
        DateTimeSwitch dateTimeSwitch = new DateTimeSwitch(timeInFuture);
        dateTimeSwitch.turnOn();
        assertTrue(dateTimeSwitch.isOn());
        dateTimeSwitch.reset();
        assertFalse(dateTimeSwitch.isOn());
    }

    @Test
    void resetShouldSetBackToInitialStateWhenInitialStateWasOn() {
        DateTimeSwitch dateTimeSwitch = new DateTimeSwitch(timeInPast);
        dateTimeSwitch.turnOff();
        assertFalse(dateTimeSwitch.isOn());
        dateTimeSwitch.reset();
        assertTrue(dateTimeSwitch.isOn());
    }
    
}
