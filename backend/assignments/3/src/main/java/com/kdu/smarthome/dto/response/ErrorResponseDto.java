package com.kdu.smarthome.dto.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@Slf4j
@AllArgsConstructor
@Builder
public class ErrorResponseDto {
    private String date;
    private String message;
    private HttpStatus status;
    private String exceptionType;
    private String stackTrace;

    public ErrorResponseDto(String message, HttpStatus status) {
        this.date = (new Date()).toString();
        this.message = message;
        this.status = status;
    }

    public ErrorResponseDto(String message, HttpStatus status, String exceptionType, String stackTrace) {
        this.date = (new Date()).toString();
        this.message = message;
        this.status = status;
        this.stackTrace = stackTrace;
        this.exceptionType = exceptionType;
    }

    public ErrorResponseDto(String message, HttpStatus status, String exceptionType) {
        this.date = (new Date()).toString();
        this.message = message;
        this.status = status;
        this.exceptionType = exceptionType;
    }

    public String toJsonString() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        map.put("date", date);
        map.put("message", message);
        map.put("status", status);
        map.put("exceptionType", exceptionType);
        map.put("stackTrace", stackTrace);

        try {
            return mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            log.error("Unable to convert Error response to Json",e);
            return null;
        }
    }
}
