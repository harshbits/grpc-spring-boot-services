package com.grpc.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Demo client applcation to test gRPC calls
 * 
 * @author harshbhavsar
 *
 */
@SpringBootApplication
@ComponentScan("com.grpc.service")
@EnableEurekaClient
public class GrpcServiceDemoClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcServiceDemoClientApplication.class, args);
	}
}
