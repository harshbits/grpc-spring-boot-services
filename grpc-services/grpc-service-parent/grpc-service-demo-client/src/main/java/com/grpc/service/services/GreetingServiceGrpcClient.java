package com.grpc.service.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.grpc.service.eureka.EurekaNameResolverProvider;
import com.grpc.service.gen.GreeterGrpc;
import com.grpc.service.gen.GreeterOuterClass.HelloReply;
import com.grpc.service.gen.GreeterOuterClass.HelloRequest;
import com.netflix.discovery.EurekaClientConfig;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.util.RoundRobinLoadBalancerFactory;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GreetingServiceGrpcClient {

	@Autowired
    private EurekaClientConfig eurekaClientConfig;
	
	private GreeterGrpc.GreeterBlockingStub greeterBlockingStub;
	
	@Value("${grpc.targetServer}")
	private String targetServer;

	public HelloReply sayHello() {

		HelloRequest.Builder builder = HelloRequest.newBuilder();
		builder.setName("Harsh");
		HelloRequest request = builder.build();
		

		if (log.isDebugEnabled()) {
			log.debug("Request " + request);
		}

		HelloReply response = greeterBlockingStub.sayHello(request);
		if (log.isDebugEnabled()) {
			log.debug("Response " + response);
		}
		return response;
	}
	
	 @PostConstruct
	    private void initializeClient() {
		 
	        ManagedChannel channel = ManagedChannelBuilder
	            .forTarget(targetServer)
	            .nameResolverFactory(new EurekaNameResolverProvider(eurekaClientConfig, "grpc.port"))
	            .loadBalancerFactory(RoundRobinLoadBalancerFactory.getInstance())
	            .usePlaintext(true)
	            .build();

	        greeterBlockingStub = GreeterGrpc.newBlockingStub(channel);
	    }
}
