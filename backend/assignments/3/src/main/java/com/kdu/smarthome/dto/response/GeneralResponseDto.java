package com.kdu.smarthome.dto.response;

import com.fasterxml.jackson.core.JsonProcessingException;
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
        Map<String, Object> stringMap = new HashMap<>();

        // Convert every value in the original map to a string
        for (Map.Entry<String, Object> entry : additionalProperties.entrySet()) {
            if(entry.getValue() instanceof Map<?,?>) {
                stringMap.put(entry.getKey(), (entry.getValue()));
            }else {
                stringMap.put(entry.getKey(), String.valueOf(entry.getValue()));
            }

        }

        // Convert message and status to string
        stringMap.put("message", String.valueOf(message));
        stringMap.put("status", String.valueOf(status));

        try {
            return mapper.writeValueAsString(stringMap);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}

