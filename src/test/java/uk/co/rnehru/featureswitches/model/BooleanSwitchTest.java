package uk.co.rnehru.featureswitches.model;

import org.junit.jupiter.api.Test;
import uk.co.rnehru.featureswitches.model.BooleanSwitch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BooleanSwitchTest {

    private static final String key = "test";

    @Test
    void isOnReturnsTrueIfSwitchIsOn() {
        BooleanSwitch booleanSwitch = new BooleanSwitch(true, key);
        assertTrue(booleanSwitch.isOn());
    }

    @Test
    void isOnReturnsFalseIfSwitchIsOff() {
        BooleanSwitch booleanSwitch = new BooleanSwitch(false, key);
        assertFalse(booleanSwitch.isOn());
    }

    @Test
    void turnOnWillChangeSwitchToBeOn() {
        BooleanSwitch booleanSwitch = new BooleanSwitch(false, key);
        booleanSwitch.turnOn();
        assertTrue(booleanSwitch.isOn());
    }

    @Test
    void turnOffWillChangeSwitchToBeOff() {
        BooleanSwitch booleanSwitch = new BooleanSwitch(true, key);
        booleanSwitch.turnOff();
        assertFalse(booleanSwitch.isOn());
    }

    @Test
    void resetShouldSetBackToInitialStateWhenInitialStateWasOff() {
        BooleanSwitch booleanSwitch = new BooleanSwitch(false, key);
        booleanSwitch.turnOn();
        assertTrue(booleanSwitch.isOn());
        booleanSwitch.reset();
        assertFalse(booleanSwitch.isOn());
    }

    @Test
    void resetShouldSetBackToInitialStateWhenInitialStateWasOn() {
        BooleanSwitch booleanSwitch = new BooleanSwitch(true, key);
        booleanSwitch.turnOff();
        assertFalse(booleanSwitch.isOn());
        booleanSwitch.reset();
        assertTrue(booleanSwitch.isOn());
    }

}
