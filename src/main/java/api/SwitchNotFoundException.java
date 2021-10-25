package api;

/**
 * Runtime exception for when a switch cannot be found
 */
public final class SwitchNotFoundException extends RuntimeException {

    /**
     * Name of the switch that could not be found.
     */
    private final String switchName;

    /** Constructor for a SwitchNotFoundException
     * @param switchName name of the switch could not be found
     * @param cause error/exception which caused the switch to not be found
     */
    public SwitchNotFoundException(final String switchName, final Throwable cause) {
        super(cause);
        this.switchName = switchName;
    }

    /**
     * Get the cause of the exception
     *
     * @return formatted message detailing which switch cannot be found
     */
    @Override
    public String getMessage() {
        return String.format("Could not find switch with name [%s]", this.switchName);
    }

}
