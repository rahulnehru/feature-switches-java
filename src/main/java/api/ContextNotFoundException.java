package api;

/**
 * Exception thrown when feature switches context could not be found
 */
public final class ContextNotFoundException extends RuntimeException {

    /**
     * Name of the context which could not be found
     */
    private final String context;

    /** Constructor for ContextNotFoundException
     * @param context name of context which could not be found
     * @param throwable error or exception which caused the exception to be thrown
     */
    public ContextNotFoundException(final String context, final Throwable throwable) {
        super(throwable);
        this.context = context;
    }

    /** Returns formatted message of exception
     * @return a string representation of the exception which was thrown containing name of context
     */
    @Override
    public String getMessage() {
        return String.format("Could not find context [%s]", context);
    }

}
