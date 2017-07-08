package com.grpc.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Demo Server Application to test GRPC Calls
 * 
 * @author harshbhavsar
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration
@ComponentScan
public class GrpcServiceDemoServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcServiceDemoServerApplication.class, args);
	}
}
