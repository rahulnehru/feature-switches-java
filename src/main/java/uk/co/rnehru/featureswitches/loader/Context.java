package uk.co.rnehru.featureswitches.loader;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigValue;
import uk.co.rnehru.featureswitches.model.BooleanSwitch;
import uk.co.rnehru.featureswitches.model.DateTimeSwitch;
import uk.co.rnehru.featureswitches.model.Switch;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * A context is defined as a logical separation of feature switches.
 */
public final class Context {

    private String contextName;
    private Map<String, Switch> switches;

    /** Getter for the name of the context
     * @return string representation of the context's name
     */
    public String getContextName() {
        return contextName;
    }

    /** Get all switches from default context
     * @return all switches from default context
     */
    public Map<String, Switch> getSwitches() {
        return switches;
    }

    /**
     * ContextBuilder utilises the HOCON scanner library to find and load the
     * feature switches from .conf files for a given context name.
     */
    public static final class ContextBuilder {
        Context c;


        /** Builds the Context from the .conf files
         * @param name name of the context to load
         * @return the context containing feature switches
         * @throws ContextLoadException when a context could not be found with name `context` from the
         * conf files on the classpath.
         */
        public Context build(final String name) throws ContextLoadException {
            try {
                this.c = new Context();
                this.c.switches = new HashMap<>();
                this.c.contextName = name;
                Map<String, ConfigValue> configLoader = new ConfigLoader(name).load();
                configLoader.forEach((key, value) -> c.switches.put(key, convertToSwitch(key, value)));
                return c;
            } catch (ConfigException e) {
                throw new ContextLoadException(name, e);
            }
        }

        static String sanitiseString(final String str) {
            return str.strip().replaceAll("(^[\"']|[\"']$)", "");
        }

        private final Function<String, Boolean> isStringyBoolean = c ->
                sanitiseString(c).equalsIgnoreCase("true")
                        || sanitiseString(c).equalsIgnoreCase("false");

        private Switch convertToSwitch(final String name, final ConfigValue configValue) {
            String value = configValue.render().strip();
            String type = configValue.valueType().name();
            if (type.equals("BOOLEAN")) {
                return new BooleanSwitch(Boolean.parseBoolean(value), name);
            }
            if (type.equals("STRING")) {
                if (isStringyBoolean.apply(value)) {
                    return new BooleanSwitch(Boolean.parseBoolean(value), name);
                }
                // Assume date switch
                var localDate = ZonedDateTime.parse(sanitiseString(value));
                return new DateTimeSwitch(localDate, name);
            }
            return new BooleanSwitch(false, name);
        }

    }

}
