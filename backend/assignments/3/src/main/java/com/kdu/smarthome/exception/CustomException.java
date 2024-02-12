package com.kdu.smarthome.exception;

import com.kdu.smarthome.util.ExceptionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * Custom exception class for handling application-specific exceptions.
 */
@SuppressWarnings("all")
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomException extends Exception{

    private final ExceptionType exceptionType;
    private HttpStatus status= HttpStatus.INTERNAL_SERVER_ERROR;

    /**
     * Constructs a new CustomException with the specified message, exception type, and HTTP status.
     *
     * @param message       The detail message.
     * @param exceptionType The type of the exception.
     * @param status        The HTTP status associated with the exception.
     */
    public CustomException(String message, ExceptionType exceptionType, HttpStatus status) {
        super(message);
        this.exceptionType=exceptionType;
        this.status=status;
    }

    /**
     * Constructs a new CustomException with the specified message and exception type.
     *
     * @param message       The detail message.
     * @param exceptionType The type of the exception.
     */
    public CustomException(String message, ExceptionType exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }
}
