package uk.co.rnehru.featureswitches.model;

import uk.co.rnehru.featureswitches.clock.TimeTravel;

import java.time.ZonedDateTime;
import java.util.concurrent.atomic.AtomicBoolean;
import static java.time.ZonedDateTime.now;

/**
 * A DateTimeSwitch is a transient switch which turns on when the current date time
 * is after the activationDateTime defined in your .conf files.
 */
public final class DateTimeSwitch implements Switch {

    private final AtomicBoolean state;
    private final ZonedDateTime activationDateTime;

    /** Constructor for DateTimeSwitch
     * @param activationDateTime the ZonedDateTime after which the switch should evaluate to true.
     */
    public DateTimeSwitch(final ZonedDateTime activationDateTime) {
        this.activationDateTime = activationDateTime;
        this.state = new AtomicBoolean(activationDateTime.isBefore(now()));
    }

    @Override
    public boolean isOn() {
        this.state.setRelease(TimeTravel.CLOCK.getTime().isAfter(activationDateTime));
        return this.state.get();
    }

    /** Getter for the ZonedDateTime after which the switch should evaluate to true
     * @return ZonedDateTime after which the switch should evaluate to true
     */
    public ZonedDateTime getActivationDateTime() {
        return this.activationDateTime;
    }
}