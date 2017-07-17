/**
 * Copyright (c) 2017 Harsh Bhavsar.  All rights reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 		
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
 * <p>
 * This configuration class is active only when the grpc-services-boot-starter for Spring
 * library is on the classpath.
 * <p>
 * Auto configuration class for gRPC services as part of grpc-services-boot-starter
 * 
 * @author harshbhavsar
 * @since 1.0.0
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