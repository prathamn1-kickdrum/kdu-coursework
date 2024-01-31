package spring.assignment.assignment2.exceptions;

import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import spring.assignment.assignment2.dto.error.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import spring.assignment.assignment2.exceptions.custom.BadRequestException;
import spring.assignment.assignment2.exceptions.custom.InvalidInputProvidedException;
import spring.assignment.assignment2.exceptions.custom.LocationNotFoundException;
import spring.assignment.assignment2.exceptions.custom.ResourceNotFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     *
     * @param locationNotFoundException
     * @return
     */
    @ExceptionHandler(value =  LocationNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCustomException(LocationNotFoundException locationNotFoundException){
        ErrorDto errorDto = new ErrorDto(locationNotFoundException.getMessage(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDto,HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @param invalidInputProvidedException
     * @return
     */
    @ExceptionHandler(value = InvalidInputProvidedException.class)
    public ResponseEntity<ErrorDto> invalidInputExceptionHandling(InvalidInputProvidedException invalidInputProvidedException){
        ErrorDto errorDto = new ErrorDto(invalidInputProvidedException.getMessage(),HttpStatus.EXPECTATION_FAILED.value());
        return new ResponseEntity<>(errorDto,HttpStatus.EXPECTATION_FAILED);
    }

    /**
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorDto> genericException(Exception e){
        ErrorDto errorDto = new ErrorDto(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorDto,HttpStatus.INTERNAL_SERVER_ERROR);
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String msg = error.getDefaultMessage();
            errors.put(fieldName,msg);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
