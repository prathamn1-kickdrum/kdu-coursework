package com.kdu.smarthome.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for ObjectMapper bean.
 */
@Configuration
public class ObjectMapperConfig {

    /**
     * Creates and configures the ObjectMapper bean.
     *
     * @return ObjectMapper bean instance.
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
