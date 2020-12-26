package com.lavanya.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.http.codec.json.Jackson2JsonDecoder;

import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;


@SpringBootApplication
@EnableFeignClients
public class WebApplication {
	
	@Bean
	Decoder decoder() {
		return new JacksonDecoder();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

}
