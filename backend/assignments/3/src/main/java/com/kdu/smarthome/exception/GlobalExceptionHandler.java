package com.kdu.smarthome.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.response.ErrorResponseDto;
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

/**
 * GlobalExceptionHandler handles exceptions occurring in the application and provides appropriate responses.
 */
@Service
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles UsernameNotFoundException and returns a ResponseEntity with appropriate error response.
     *
     * @param exception The UsernameNotFoundException instance.
     * @return ResponseEntity containing the error response.
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> handleUsernameNotFound(UsernameNotFoundException exception) {
        return new ResponseEntity<>(new ErrorResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles HttpRequestMethodNotSupportedException and returns a ResponseEntity with appropriate error response.
     *
     * @param e The HttpRequestMethodNotSupportedException instance.
     * @return ResponseEntity containing the error response.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleMethodNotAllowed(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body("wrong Http method used: " + e.getMessage());
    }

    /**
     * Handles ResourceNotFoundException and returns a ResponseEntity with appropriate error response.
     *
     * @param e The ResourceNotFoundException instance.
     * @return ResponseEntity containing the error response.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    /**
     * Handles MethodArgumentNotValidException and returns a ResponseEntity with appropriate error response.
     *
     * @param e The MethodArgumentNotValidException instance.
     * @return ResponseEntity containing the error response.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String msg = error.getDefaultMessage();
            errors.put(fieldName, msg);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    /**
     * Handles ExpiredJwtException and returns a ResponseEntity with appropriate error response.
     *
     * @param e The ExpiredJwtException instance.
     * @return ResponseEntity containing the error response.
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponseDto> handleExpiredJwtException(ExpiredJwtException e) {
        return ResponseEntity.internalServerError().body(new ErrorResponseDto("JWT token expired. Please login or refresh again.", HttpStatus.INTERNAL_SERVER_ERROR));
    }

    /**
     * Handles JsonProcessingException and returns a ResponseEntity with appropriate error response.
     *
     * @param e The JsonProcessingException instance.
     * @return ResponseEntity containing the error response.
     */
    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ErrorResponseDto> handleJsonProcessingException(JsonProcessingException e) {
        return ResponseEntity.internalServerError().body(new ErrorResponseDto("Can't convert the object to Json", HttpStatus.INTERNAL_SERVER_ERROR));
    }

    /**
     * Handles NumberFormatException and returns a ResponseEntity with appropriate error response.
     *
     * @param e The NumberFormatException instance.
     * @return ResponseEntity containing the error response.
     */
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorResponseDto> handleNumberFormatException(NumberFormatException e) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto("Invalid value for ID", HttpStatus.BAD_REQUEST));
    }

    /**
     * Handles CustomException and returns a ResponseEntity with appropriate error response.
     *
     * @param e The CustomException instance.
     * @return ResponseEntity containing the error response.
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException e) {
        log.error("Custom exception occurred: Type - {}, Message - {}", e.getExceptionType(), e.getMessage(), e);
        ErrorResponseDto errorDto = ErrorResponseDto.builder()
                .message(e.getMessage())
                .status(e.getStatus())
                .stackTrace(Arrays.toString(e.getStackTrace()))
                .exceptionType(String.valueOf(e.getExceptionType()))
                .build();
        return ResponseEntity.status(e.getStatus()).body(errorDto.toJsonString());

    }
}
