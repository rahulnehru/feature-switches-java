package loader;

/**
 * Checked exception thrown when a context fails to load
 */
public final class ContextLoadException extends Exception {

    /**
     * Name of the switch which could not be loaded.
     */
    private final String contextToLoad;

    /** Constructor for ContextLoadException
     * @param contextToLoad name of context which could not be loaded
     * @param throwable error or exception which caused the context to not be loaded correctly
     */
    public ContextLoadException(final String contextToLoad, final Throwable throwable) {
        super(throwable);
        this.contextToLoad = contextToLoad;
    }

    /** Returns formatted message of exception
     * @return a string representation of the exception which was thrown containing name of context
     */
    @Override
    public String getMessage() {
        return String.format("Could not load context: [%s]", contextToLoad);
    }

}
