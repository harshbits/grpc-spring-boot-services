package com.grpc.service;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.grpc.service.support.GrpcService;

/**
 * Auto configuration class for gRPC services as part of grpc-boot
 * 
 * @author harshbhavsar
 *
 */
@Configuration
@EnableConfigurationProperties(GrpcServerProperties.class)
@AutoConfigureOrder
public class GrpcAutoConfiguration {

	@Bean
	@ConditionalOnBean(annotation = GrpcService.class)
	public GrpcServerRunner grpcServerRunner(GrpcServerBuilderConfigurer configurer) {
		return new GrpcServerRunner(configurer);
	}

	@Bean
	@ConditionalOnMissingBean(GrpcServerBuilderConfigurer.class)
	public GrpcServerBuilderConfigurer serverBuilderConfigurer() {
		return new GrpcServerBuilderConfigurer();
	}
}