package com.kdu.smarthome.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonUtils {

    private JsonUtils() {}

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String convertObjectToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static String convertListToJson(List<?> list) throws JsonProcessingException {
        return objectMapper.writeValueAsString(list);
    }
}

