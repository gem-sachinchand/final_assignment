package com.example.eventMangement;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EventMangementApplication {

	@Bean
	public ModelMapper modelMapper(){return new ModelMapper();};

	public static void main(String[] args) {
		SpringApplication.run(EventMangementApplication.class, args);
	}

}
