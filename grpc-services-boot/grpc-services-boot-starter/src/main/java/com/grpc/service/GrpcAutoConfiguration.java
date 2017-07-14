package com.grpc.service;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.grpc.service.support.GrpcService;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for io.grpc's gRPC support.
 * 
 * Auto configuration class for gRPC services as part of grpc-services-boot-starter
 * 
 * @author harshbhavsar
 * @since 1.0.0
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

	/**
	 * Create a {@link GrpcServerBuilderConfigurer} if necessary.
	 * @return {@literal null} if no cluster settings are set.
	 */
	@Bean
	@ConditionalOnMissingBean(GrpcServerBuilderConfigurer.class)
	public GrpcServerBuilderConfigurer serverBuilderConfigurer() {
		return new GrpcServerBuilderConfigurer();
	}
}