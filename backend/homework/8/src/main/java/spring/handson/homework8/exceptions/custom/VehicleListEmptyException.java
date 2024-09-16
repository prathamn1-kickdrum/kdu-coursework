package spring.handson.homework8.exceptions.custom;

/**
 * exception thrown if vehicle list is empty
 */
public class VehicleListEmptyException extends Exception {

    public VehicleListEmptyException() {
        super();
    }

    public VehicleListEmptyException(String message) {
        super(message);
    }

    public VehicleListEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public VehicleListEmptyException(Throwable cause) {
        super(cause);
    }
}

