package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BooleanSwitchTest {

    @Test
    void isOnReturnsTrueIfSwitchIsOn() {
        BooleanSwitch booleanSwitch = new BooleanSwitch(true);
        assertTrue(booleanSwitch.isOn());
    }

    @Test
    void isOnReturnsFalseIfSwitchIsOff() {
        BooleanSwitch booleanSwitch = new BooleanSwitch(false);
        assertFalse(booleanSwitch.isOn());
    }

    @Test
    void turnOnWillChangeSwitchToBeOn() {
        BooleanSwitch booleanSwitch = new BooleanSwitch(false);
        booleanSwitch.turnOn();
        assertTrue(booleanSwitch.isOn());
    }

    @Test
    void turnOffWillChangeSwitchToBeOff() {
        BooleanSwitch booleanSwitch = new BooleanSwitch(true);
        booleanSwitch.turnOff();
        assertFalse(booleanSwitch.isOn());
    }

    @Test
    void resetShouldSetBackToInitialStateWhenInitialStateWasOff() {
        BooleanSwitch booleanSwitch = new BooleanSwitch(false);
        booleanSwitch.turnOn();
        assertTrue(booleanSwitch.isOn());
        booleanSwitch.reset();
        assertFalse(booleanSwitch.isOn());
    }

    @Test
    void resetShouldSetBackToInitialStateWhenInitialStateWasOn() {
        BooleanSwitch booleanSwitch = new BooleanSwitch(true);
        booleanSwitch.turnOff();
        assertFalse(booleanSwitch.isOn());
        booleanSwitch.reset();
        assertTrue(booleanSwitch.isOn());
    }

}
