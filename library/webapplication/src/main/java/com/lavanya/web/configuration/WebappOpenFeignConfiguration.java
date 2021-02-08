package com.lavanya.web.configuration;

import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.cloud.openfeign.support.SortJacksonModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

/**
 * Configuration to overrides WebSecurityConfigurerAdapter for spring security.
 * @author lavanya
 */
@Configuration
public class WebappOpenFeignConfiguration {
	
	
	 

	@Bean
    public PageJacksonModule pageJacksonModule() {
        return new PageJacksonModule();
    }

    @Bean
    public SortJacksonModule sortJacksonModule() {
        return new SortJacksonModule();
    }
    
    
   
    
//    @Bean
//    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
//        return new BasicAuthRequestInterceptor("username", "password");
//    }
	

}
