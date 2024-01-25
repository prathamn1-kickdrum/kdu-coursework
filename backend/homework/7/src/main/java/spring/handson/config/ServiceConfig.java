package spring.handson.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * scans @component classes in speaker and service package
 */
@Configuration
@ComponentScan(basePackages = "spring.handson.service")
public class ServiceConfig {
}
