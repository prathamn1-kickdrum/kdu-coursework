package spring.handson.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * scans @component classes in speaker and tyre package
 */
@Configuration
@ComponentScan(basePackages = {"spring.handson.model.speaker","spring.handson.model.tyre"})
public class EntityConfig {
}
