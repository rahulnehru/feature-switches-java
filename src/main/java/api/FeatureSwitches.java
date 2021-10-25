package api;

import loader.Context;
import loader.ContextLoadException;
import model.Switch;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Concrete final instance of Switches. This cannot be instantiated by client
 * libraries. This is intended to be a singleton as feature switches being used
 * should always have consistent state. If you want your own implementation for
 * testing purposes, create your own implementation of the interface.
 */
public final class FeatureSwitches implements Switches {

    private static final String DEFAULT_CONTEXT_NAME = "switches.default";
    private final Context defaultContext;
    private final List<Context> contexts = new ArrayList<>();
    private static FeatureSwitches instance;

    private FeatureSwitches() {
        try {
            this.defaultContext = new Context.ContextBuilder().build(DEFAULT_CONTEXT_NAME);
            contexts.add(defaultContext);
        } catch (ContextLoadException e) {
            throw new ContextNotFoundException(DEFAULT_CONTEXT_NAME, e);
        }
    }

    /**
     * Evaluate all feature switches in default context from .conf files in class path and create a singleton
     * instance that can be used in the client code base.
     *
     * @return singleton instance of FeatureSwitches
     */
    public static FeatureSwitches getInstance() {
        if (instance == null) {
            instance = new FeatureSwitches();
        }
        return instance;
    }

    private Context loadIfNewContext(final String context) throws ContextLoadException {
        if (context.equals(DEFAULT_CONTEXT_NAME)) {
            return defaultContext;
        }
        Context toReturn;
        if (contexts.stream().noneMatch(c -> Objects.equals(c.getContextName(), context))) {
            toReturn = new Context.ContextBuilder().build(context);
            contexts.add(toReturn);
        } else {
            toReturn = contexts.stream().filter(c -> c.getContextName().equals(context)).findFirst().orElseThrow();
        }
        return toReturn;
    }

    @Override
    public Map<String, Switch> getAllSwitches() {
        return getAllSwitches(DEFAULT_CONTEXT_NAME);
    }

    @Override
    public Map<String, Switch> getAllSwitches(String context) {

        return contexts
                .stream()
                .filter(c -> c.getContextName().equals(context))
                .findFirst()
                .map(Context::getSwitches)
                .orElseThrow(() -> {
                    throw new ContextNotFoundException(context, new NoSuchElementException());
                });
    }

    @Override
    public Switch getSwitch(String path) {
        return getSwitchFromContext(path).apply(defaultContext);
    }

    @Override
    public Switch getSwitch(String context, String path) {
        try {
            return getSwitchFromContext(path).apply(loadIfNewContext(context));
        } catch (ContextLoadException e) {
            throw new SwitchNotFoundException(path, e);
        }
    }

    @Override
    public void turnAllOn() {
        turnAllFromContext(Switch::turnOn).accept(defaultContext);
    }

    @Override
    public void turnAllOn(String context) {
        try {
            turnAllFromContext(Switch::turnOn).accept(loadIfNewContext(context));
        } catch (ContextLoadException e) {
            throw new ContextNotFoundException(context, e);
        }
    }

    @Override
    public void turnAllOff() {
        turnAllFromContext(Switch::turnOff).accept(defaultContext);
    }

    @Override
    public void turnAllOff(String context) {
        try {
            turnAllFromContext(Switch::turnOff).accept(loadIfNewContext(context));
        } catch (ContextLoadException e) {
            throw new ContextNotFoundException(context, e);
        }
    }

    @Override
    public void resetAll() {
        resetAll(DEFAULT_CONTEXT_NAME);
    }

    @Override
    public void resetAll(String context) {
        getAllSwitches(context).forEach((key, value) -> value.reset());
    }

    private Function<Context, Switch> getSwitchFromContext(String path) {
        return context -> {
            try {
                Switch s = context.getSwitches().get(path);
                assert (s != null);
                return s;
            } catch (Throwable e) {
                throw new SwitchNotFoundException(path, e);
            }
        };

    }

    private Consumer<Context> turnAllFromContext(final Consumer<Switch> f) {
        return context -> context.getSwitches().forEach((s, aSwitch) -> f.accept(aSwitch));
    }


}
