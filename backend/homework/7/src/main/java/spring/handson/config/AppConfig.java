package spring.handson.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * App config class: imports configs of service, repo, entity packages
 */
@Configuration
@Import({EntityConfig.class,RepoConfig.class,ServiceConfig.class})
public class AppConfig {
}
