package com.lavanya.api;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootApplication
@EnableFeignClients("com.lavanya")
public class LibraryApplication {
	
//	@Bean
//	public ObjectMapper serializingObjectMapper() {
//	    ObjectMapper objectMapper = new ObjectMapper();
//	    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//	    objectMapper.registerModule(new JavaTimeModule());
//	    return objectMapper;
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

}
