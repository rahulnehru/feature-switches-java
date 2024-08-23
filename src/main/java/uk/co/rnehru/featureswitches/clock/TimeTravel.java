package uk.co.rnehru.featureswitches.clock;

import java.time.Clock;
import java.time.Duration;
import java.time.ZonedDateTime;

/**
 * TimeTravel is a singleton enum which contains a clock that can be manipulated for testing Date based feature switches.
 */
public enum TimeTravel {
    /**
     * Singleton instance of TimeTravel. This instance contains the clock which can be manipulated for testing purposes.
     */
    CLOCK;

    private Clock time;
    private boolean running;

    TimeTravel() {
        this.time = Clock.systemDefaultZone();
        this.running = false;
    }

    /**
     * Set the time of the clock to the given ZonedDateTime.
     * @param time ZonedDateTime to set the clock to
     * @param running whether the clock should be running or not
     */
    public final void setTime(ZonedDateTime time, boolean running) {
        if (!running) {
            this.time = Clock.fixed(time.toInstant(), time.getZone());
        } else {
            Duration difference = java.time.Duration.between(ZonedDateTime.now(), time);
            this.time = Clock.offset(Clock.systemDefaultZone(), difference);
        }
    }

    /**
     * Get the current time of the clock.
     * @return the current time of the clock
     */
    public final ZonedDateTime getTime() {
        return this.time.instant().atZone(this.time.getZone());
    }

    /**
     * Reset the clock to the system default time.
     */
    public final void reset() {
        this.time = Clock.systemDefaultZone();
        this.running = true;
    }
}