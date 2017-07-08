package com.grpc.service.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Service;

import com.grpc.service.gen.GreeterGrpc;
import com.grpc.service.gen.GreeterOuterClass.HelloReply;
import com.grpc.service.gen.GreeterOuterClass.HelloRequest;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * Greeting service Grpc Client
 * 
 * @author harshbhavsar
 *
 */
@Service
@Slf4j
@EnableEurekaClient
public class GreetingServiceGrpcClient {

	@Autowired
	private EurekaClient client;

	private GreeterGrpc.GreeterBlockingStub greeterBlockingStub;

	@Value("${grpc.targetServer}")
	private String targetServer;

	public HelloReply sayHello() {

		HelloRequest.Builder builder = HelloRequest.newBuilder();
		builder.setName("Harsh");
		HelloRequest request = builder.build();

		if (log.isDebugEnabled()) {
			log.debug("Request {}", request);
		}

		HelloReply response = greeterBlockingStub.sayHello(request);
		if (log.isDebugEnabled()) {
			log.debug("Response {}", response);
		}
		return response;
	}

	@PostConstruct
	private void initializeClient() {

		final InstanceInfo instanceInfo = client.getNextServerFromEureka(targetServer, false);
		final ManagedChannel channel = ManagedChannelBuilder
				.forAddress(instanceInfo.getIPAddr(), instanceInfo.getPort()).usePlaintext(true).build();

		greeterBlockingStub = GreeterGrpc.newBlockingStub(channel);
	}
}
