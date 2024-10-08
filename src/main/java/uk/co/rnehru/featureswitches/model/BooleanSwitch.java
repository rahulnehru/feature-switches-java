package uk.co.rnehru.featureswitches.model;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A BooleanSwitch contains only two modes, true or false.
 * It is used for simple on/off functionality based on the state of the
 * switch in your .conf files.
 */
public final class BooleanSwitch implements Switch {

    private final boolean initialState;
    private final String name;

    /** Instantiate a new BooleanSwitch object.
     * @param initialState the initial state of whether it should be on or off
     */
    public BooleanSwitch(final boolean initialState, final String name) {
        this.initialState = initialState;
        this.state = new AtomicBoolean(initialState);
        this.name = name;
    }

    private final AtomicBoolean state;

    /**
     * @return whether the switch is currently on or off.
     */
    @Override
    public boolean isOn() {
        return state.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Turn on the switch.
     */
    public void turnOn() {
        state.setRelease(true);
    }

    /**
     * Turn off the switch.
     */
    public void turnOff() {
        state.setRelease(false);
    }

    /**
     * Reset the switch to its initial state.
     */
    public void reset() {
        state.setRelease(initialState);
    }

}
