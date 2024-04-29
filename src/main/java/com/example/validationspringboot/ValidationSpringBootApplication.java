package com.example.validationspringboot;

import com.example.validationspringboot.entity.User;
import com.example.validationspringboot.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ValidationSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValidationSpringBootApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(UserRepository userRepository) {
		return (String[] args) -> {
			User user1 = new User("Bob", "bob@domain.com");
			User user2 = new User("Jenny", "jenny@domain.com");
			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.findAll().forEach(System.out::println);
		};
	}

}
