package uk.co.rnehru.featureswitches.model;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A BooleanSwitch contains only two modes, true or false.
 * It is used for simple on/off functionality based on the state of the
 * switch in your .conf files.
 */
public final class BooleanSwitch implements Switch {

    private final boolean initialState;

    /** Instantiate a new BooleanSwitch object.
     * @param initialState the initial state of whether it should be on or off
     */
    public BooleanSwitch(final boolean initialState) {
        this.initialState = initialState;
        this.state = new AtomicBoolean(initialState);
    }

    private final AtomicBoolean state;

    @Override
    public boolean isOn() {
        return state.get();
    }

    @Override
    public void turnOn() {
        state.setRelease(true);
    }

    @Override
    public void turnOff() {
        state.setRelease(false);
    }

    @Override
    public void reset() {
        state.setRelease(initialState);
    }

}
