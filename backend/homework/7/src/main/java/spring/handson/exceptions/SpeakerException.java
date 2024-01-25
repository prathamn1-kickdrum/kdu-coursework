package spring.handson.exceptions;

/**
 * exceptions related to speaker, though not used
 */
public class SpeakerException extends Exception {
    public SpeakerException() {
        super();
    }

    public SpeakerException(String message) {
        super(message);
    }

    public SpeakerException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpeakerException(Throwable cause) {
        super(cause);
    }
}
