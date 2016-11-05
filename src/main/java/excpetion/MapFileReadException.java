package exception;

/**
 * Exception class for when a map file is incorrect
 * @author Ryan Voor
 * @version 1.0
 */
public class MapFileReadException extends Exception {
    public MapFileReadException(String message) {
        super(message);
    }
}
