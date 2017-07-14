package com.grpc.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Configuration property class for gRPC server
 * 
 * @author harshbhavsar
 * @since 1.0.0
 *
 */
@Data
@ConfigurationProperties("grpc")
public class GrpcServerProperties {
	
	/**
	 * gRPC server port
	 */
	private int port = 6565;

}