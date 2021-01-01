package com.lavanya.web;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.http.codec.json.Jackson2JsonDecoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;


@SpringBootApplication
@EnableFeignClients
public class WebApplication {
	
	@Bean
	Decoder decoder() {
		return new JacksonDecoder();
	}
	
	
	
//	@Bean
//	public ObjectMapper serializingObjectMapper() {
//	    ObjectMapper objectMapper = new ObjectMapper();
//	    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//	    objectMapper.registerModule(new JavaTimeModule());
//	    return objectMapper;
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
	
}
