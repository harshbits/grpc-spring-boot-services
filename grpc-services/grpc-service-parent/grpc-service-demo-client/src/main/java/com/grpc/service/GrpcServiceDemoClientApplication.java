package com.grpc.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableDiscoveryClient
public class GrpcServiceDemoClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcServiceDemoClientApplication.class, args);
	}
}
