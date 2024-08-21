package spring.handson.exceptions;

/**
 * exceptions related to tyres
 */
public class TyreException extends Exception {

    // Constructors

    public TyreException() {
        super();
    }

    public TyreException(String message) {
        super(message);
    }

    public TyreException(String message, Throwable cause) {
        super(message, cause);
    }

    public TyreException(Throwable cause) {
        super(cause);
    }
}
