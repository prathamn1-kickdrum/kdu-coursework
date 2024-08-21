package spring.handson.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * scans @component classes in repo package
 */
@Configuration
@ComponentScan(basePackages = "spring.handson.repo")
public class RepoConfig {
}
