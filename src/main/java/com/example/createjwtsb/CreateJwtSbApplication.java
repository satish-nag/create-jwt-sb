package com.example.createjwtsb;

import com.example.createjwtsb.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CreateJwtSbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreateJwtSbApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepository){
		return args -> {
			System.out.println(userRepository.findById(3));
		};
	}*/

}
