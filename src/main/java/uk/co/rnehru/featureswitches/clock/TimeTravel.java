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
        this.running = true;
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
     * Set the running state of the clock. If running is set to false, the clock will be fixed at the current time.
     * If running is set to true, the clock will be offset by the difference between the current time and the time set
     * and will continue to run.
     * @param running whether the clock should be running or not
     */
    public final void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Get the current time of the clock.
     * @return the current time of the clock
     */
    public final ZonedDateTime getTime() {
        return this.time.instant().atZone(this.time.getZone());
    }

    /**
     * Get the running state of the clock.
     * @return the running state of the clock
     */
    public final boolean isRunning() {
        return this.running;
    }

    /**
     * Reset the clock to the system default time.
     */
    public final void reset() {
        this.time = Clock.systemDefaultZone();
        this.running = true;
    }
}