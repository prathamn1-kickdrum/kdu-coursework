package spring.handson1.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = "spring.handson1.services")
public class AppConfig {
}
