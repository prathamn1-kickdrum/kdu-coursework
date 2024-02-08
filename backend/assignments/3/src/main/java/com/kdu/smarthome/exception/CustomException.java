package com.kdu.smarthome.exception;

import com.kdu.smarthome.util.ExceptionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.aspectj.apache.bcel.ExceptionConstants;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomException extends RuntimeException{
    private final ExceptionType exceptionType;
    private HttpStatus status= HttpStatus.INTERNAL_SERVER_ERROR;
    public CustomException(String message, ExceptionType exceptionType, HttpStatus status) {
        super(message);
        this.exceptionType=exceptionType;
        this.status=status;
    }
    public CustomException(String message, ExceptionType exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }
}
