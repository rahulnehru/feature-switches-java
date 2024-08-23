package uk.co.rnehru.featureswitches.model;

/**
 * A switch can be used to drive functionality in your code base.
 * This is an interface which defines behaviour to define whether it is logically activated, or for testing purposes
 * can be turned on or off accordingly.
 */
public interface Switch {

    /** Returns if the current state of the switch is on or off.
     * @return whether or not the switch currently evaluates to true/false
     */
    boolean isOn();

    /**
     * Turn on the feature switch, such that isOn() returns true in further evaluation.
     */
    void turnOn();

    /**
     * Turn off the feature switch, such that isOff() returns true in further evaluation.
     */
    void turnOff();

    /**
     * Re-evaluate feature switch based on configuration passed in .conf file.
     */
    void reset();

}
