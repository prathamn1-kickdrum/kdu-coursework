package com.kdu.smarthome.exception;

import com.kdu.smarthome.dto.response.ErrorResponseDto;
import com.kdu.smarthome.exception.custom.BadLoginCredentialsException;
import com.kdu.smarthome.exception.custom.ResourceNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> handleUsernameNotFound(UsernameNotFoundException exception) {
        return new ResponseEntity<>(new ErrorResponseDto(exception.getMessage(),HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleBadLoginCredentialsException(BadLoginCredentialsException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponseDto(exception.getMessage(),HttpStatus.FORBIDDEN));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponseDto> handleExpiredJwtException(ExpiredJwtException e) {
        return ResponseEntity.internalServerError().body(new ErrorResponseDto("JWT token expired. Please login or refresh again.",HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException e) {
        log.error("Custom exception occurred: Type - {}, Message - {}", e.getExceptionType(), e.getMessage(), e);
        ErrorResponseDto errorDto = ErrorResponseDto.builder()
                .message(e.getMessage())
                .status(e.getStatus())
                .stackTrace(Arrays.toString(e.getStackTrace()))
                .exceptionType(e.getExceptionType())
                .build();
        return ResponseEntity.status(e.getStatus()).body(errorDto.toString());

    }
}