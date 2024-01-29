package spring.handson.homework9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("prod")
public class Homework9Application {

	public static void main(String[] args) {
		SpringApplication.run(Homework9Application.class, args);
	}

}
