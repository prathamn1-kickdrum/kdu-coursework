package com.kdu.smarthome;

import com.kdu.smarthome.service.impl.StartupDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmartHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartHomeApplication.class, args);
    }
    @Bean
    public CommandLineRunner startup(StartupDataService startupDataService) {
        return args -> startupDataService.run();
    }

}
