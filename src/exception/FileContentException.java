/**
 * 
 */
package exception;

/**
 * @author machupicchubeta
 */
public class FileContentException extends RuntimeException {
    public FileContentException(final String message) {
        super(message);
    }
    public FileContentException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
