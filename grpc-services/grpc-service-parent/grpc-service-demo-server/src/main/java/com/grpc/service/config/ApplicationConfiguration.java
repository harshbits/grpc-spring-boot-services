package com.grpc.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.grpc.service.services.GreetingService;

@Configuration
public class ApplicationConfiguration {
	
	@Bean
	public GreetingService greetingService(){
		return new GreetingService();
	}

}
