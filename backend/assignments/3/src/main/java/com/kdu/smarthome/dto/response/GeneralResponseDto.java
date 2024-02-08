package com.kdu.smarthome.dto.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j


public class GeneralResponseDto {
    private String message;
    private HttpStatus status;
    private Map<String, Object> additionalProperties = new HashMap<>();


    public void addProperty(String key, Object value) {
        additionalProperties.put(key, value);
    }

    // Convert object to JSON string
    public String toJsonString() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status);
        map.putAll(additionalProperties); // Add additional properties

        try {
            return mapper.writeValueAsString(map);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}

