package uk.co.rnehru.featureswitches.model;

/**
 * A switch can be used to drive functionality in your code base.
 * This is an interface which defines behaviour to define whether it is logically activated, or for testing purposes
 * can be turned on or off accordingly.
 */
public interface Switch {

    /** Returns if the current state of the switch is on or off.
     * @return whether the switch currently evaluates to true/false
     */
    boolean isOn();


}
