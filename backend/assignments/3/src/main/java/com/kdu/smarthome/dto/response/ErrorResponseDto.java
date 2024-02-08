package com.kdu.smarthome.dto.response;

import com.kdu.smarthome.util.ExceptionType;
import jdk.jfr.StackTrace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponseDto {
    private String date;
    private String message;
    private HttpStatus status;
    private ExceptionType exceptionType;
    private String stackTrace;
    public ErrorResponseDto(String message, HttpStatus status) {
        this.date = (new Date()).toString();
        this.message=message;
        this.status=status;
    }
    public ErrorResponseDto(String message, HttpStatus status,ExceptionType exceptionType, String stackTrace) {
        this.date = (new Date()).toString();
        this.message=message;
        this.status=status;
        this.stackTrace=stackTrace;
        this.exceptionType=exceptionType;
    }

    public ErrorResponseDto(String message, HttpStatus status, ExceptionType exceptionType) {
        this.date = (new Date()).toString();
        this.message=message;
        this.status=status;
        this.exceptionType=exceptionType;
    }

}
