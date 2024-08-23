package uk.co.rnehru.featureswitches.clock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class TimeTravelTest {

    @BeforeEach
    public void setUp() {
        TimeTravel.CLOCK.reset();
    }

    @Test
    public void testGetTimeReturnsCurrentTime() {
        TimeTravel timeTravel = TimeTravel.CLOCK;
        assertTrue(timeTravel.getTime().minusMinutes(1).isBefore(ZonedDateTime.now()));
        assertTrue(timeTravel.getTime().plusMinutes(1).isAfter(ZonedDateTime.now()));
    }

    @Test
    public void testSetTimeSetsTimeWhenRunningFalse() {
        TimeTravel timeTravel = TimeTravel.CLOCK;
        ZonedDateTime time = ZonedDateTime.now().minusDays(1);
        timeTravel.setTime(time, false);
        assertEquals(time, timeTravel.getTime());
    }

    @Test
    public void testSetTimeSetsTimeWhenRunningTrue() {
        TimeTravel timeTravel = TimeTravel.CLOCK;
        ZonedDateTime time = ZonedDateTime.now().minusDays(1);
        timeTravel.setTime(time, true);
        assertTrue(timeTravel.getTime().isBefore(ZonedDateTime.now()));
        assertTrue(timeTravel.getTime().isAfter(time));
    }

    @Test
    public void testResetSetsTimeToCurrentTime() {
        TimeTravel timeTravel = TimeTravel.CLOCK;
        ZonedDateTime time = ZonedDateTime.now().minusDays(1);
        timeTravel.setTime(time, false);
        timeTravel.reset();
        assertTrue(timeTravel.getTime().minusMinutes(1).isBefore(ZonedDateTime.now()));
        assertTrue(timeTravel.getTime().plusMinutes(1).isAfter(ZonedDateTime.now()));
    }
}
