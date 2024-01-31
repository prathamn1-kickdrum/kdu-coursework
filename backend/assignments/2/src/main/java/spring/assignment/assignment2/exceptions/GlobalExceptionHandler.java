package spring.assignment.assignment2.exceptions;

import spring.assignment.assignment2.dto.error.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import spring.assignment.assignment2.exceptions.custom.InvalidInputProvidedException;
import spring.assignment.assignment2.exceptions.custom.LocationNotFoundException;

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
}
