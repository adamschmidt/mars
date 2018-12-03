package exercise;

/**
 * Base exception class for problems with Rover processing
 */
public class RoverException extends Exception {

    /**
     * Create a Rover exception with a specified message
     *
     * @param message the message
     */
    public RoverException(String message) {
        super(message);
    }

    /**
     * Create a Rover exception with the specified message and reason
     *
     * @param message the message
     * @param cause the cause
     */
    public RoverException(String message, Throwable cause) {
        super(message, cause);
    }
}
