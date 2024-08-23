package uk.co.rnehru.featureswitches.api;

import uk.co.rnehru.featureswitches.model.Switch;

import java.util.Map;

/**
 * Interface for interacting with a Switches context.
 */
interface Switches {

    /**
     * Get switch by name from default context.
     *
     * @param path name of switch
     * @return a switch from the default context
     */
    Switch getSwitch(String path);

    /**
     * Get switch by name from named context.
     *
     * @param context name of the context
     * @param path name of switch
     * @return a switch from the named context
     */
    Switch getSwitch(String context, String path);

    /**
     * Get all switches from default context.
     *
     * @return all switches from the named context
     */
    Map<String, Switch> getAllSwitches();

    /**
     * Get all switches from named context.
     *
     * @param context name of context
     * @return all switched from the named context
     */
    Map<String, Switch> getAllSwitches(String context);

    /**
     * Turn on all switches in default context
     */
    void turnAllOn();


    /**
     * Turn on all switches in named context
     *
     * @param context name of context
     */
    void turnAllOn(String context);

    /**
     * Turn off all switches in default context
     */
    void turnAllOff();

    /**
     * Turn off all switches in named context
     *
     * @param context name of context
     */
    void turnAllOff(String context);

    /**
     * Re-evaluate all switches in default context based on conf file
     */
    void resetAll();

    /**
     * Re-evaluate all switches in named context based on conf file
     *
     * @param context name of context
     */
    void resetAll(String context);

}
