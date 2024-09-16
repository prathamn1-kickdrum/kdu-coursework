package spring.handson.homework8.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.handson.homework8.exceptions.custom.BadRequestException;
import spring.handson.homework8.exceptions.custom.ResourceNotFoundException;
import spring.handson.homework8.exceptions.custom.VehicleListEmptyException;
import spring.handson.homework8.exceptions.custom.VehicleObjectNullException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(VehicleListEmptyException.class)
    public ResponseEntity<String> handleListEmptyException(VehicleListEmptyException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error! No vehicles in store." + e.getMessage());
    }

    @ExceptionHandler(VehicleObjectNullException.class)
    public ResponseEntity<String> handleListEmptyException(VehicleObjectNullException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Vehicle object is Null." + e.getMessage());
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<String> handleIndexOutOfBoundException(IndexOutOfBoundsException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("no vehicle with specified index present in store."+e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleMethodNotAllowed(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body("wrong Http method used: " + e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
