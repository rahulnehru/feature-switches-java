package uk.co.rnehru.featureswitches.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.co.rnehru.featureswitches.clock.TimeTravel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DateTimeSwitchTest {

    @BeforeEach
    void setUp() {
        TimeTravel.CLOCK.reset();
    }

    private final ZonedDateTime timeInPast = ZonedDateTime.now().minusYears(1);
    private final ZonedDateTime timeInFuture = ZonedDateTime.now().plusYears(1);

    private final String key = "test";

    @Test
    void isOnReturnsTrueIfSwitchIsOn() {
        DateTimeSwitch dateTimeSwitch = new DateTimeSwitch(timeInPast, key);
        assertTrue(dateTimeSwitch.isOn());
    }

    @Test
    void isOnReturnsFalseIfSwitchIsOff() {
        DateTimeSwitch dateTimeSwitch = new DateTimeSwitch(timeInFuture, key);
        assertFalse(dateTimeSwitch.isOn());
    }

    @Test
    void isOnReturnsTrueIfSwitchDateIsRunningAndPassesActivationDateTime() throws InterruptedException {
        DateTimeSwitch dateTimeSwitch = new DateTimeSwitch(timeInFuture, key);
        assertFalse(dateTimeSwitch.isOn());
        TimeTravel.CLOCK.setTime(timeInFuture.minusSeconds(2), true);
        assertFalse(dateTimeSwitch.isOn());
        Thread.sleep(2000);
        assertTrue(dateTimeSwitch.isOn());
        TimeTravel.CLOCK.reset();
    }

    @Test
    void getActivationDateTimeReturnsZonedDateTimeOfSwitch() {
        ZonedDateTime firstOfJan2020 = ZonedDateTime.of(LocalDate.of(2020, 1, 1), LocalTime.ofSecondOfDay(0), ZoneId.of("UTC"));
        DateTimeSwitch dateTimeSwitch = new DateTimeSwitch(firstOfJan2020, key);
        ZonedDateTime actual = dateTimeSwitch.getActivationDateTime();
        assertEquals(firstOfJan2020, actual);
    }


}
