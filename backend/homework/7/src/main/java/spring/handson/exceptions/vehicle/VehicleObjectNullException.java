package spring.handson.exceptions.vehicle;

/**
 * exception thrown for null vehicle object
 */
public class VehicleObjectNullException extends Exception {

    public VehicleObjectNullException() {
        super();
    }

    public VehicleObjectNullException(String message) {
        super(message);
    }

    public VehicleObjectNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public VehicleObjectNullException(Throwable cause) {
        super(cause);
    }
}