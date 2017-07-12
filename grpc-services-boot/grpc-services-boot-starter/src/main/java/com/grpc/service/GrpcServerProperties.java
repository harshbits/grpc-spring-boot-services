package com.grpc.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration property class for gRPC server
 * 
 * @author harshbhavsar
 * @since 1.0.0
 *
 */
@ConfigurationProperties("grpc")
public class GrpcServerProperties {
    /**
     * gRPC server port
     */
    private int port = 6565;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}