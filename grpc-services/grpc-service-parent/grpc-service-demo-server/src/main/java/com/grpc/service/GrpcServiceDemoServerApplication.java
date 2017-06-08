package com.grpc.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class GrpcServiceDemoServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcServiceDemoServerApplication.class, args);
	}
}
