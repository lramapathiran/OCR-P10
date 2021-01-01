package com.lavanya.web.configuration;

import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class FeignConfiguration {
	
	@Bean
	public PageJacksonModule pageJacksonModule() {
	    return new PageJacksonModule();
	}
	

}
