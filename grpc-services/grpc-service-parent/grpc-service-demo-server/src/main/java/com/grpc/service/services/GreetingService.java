package com.grpc.service.services;

import com.grpc.service.gen.GreeterGrpc;
import com.grpc.service.gen.GreeterOuterClass;
import com.grpc.service.support.GrpcService;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@GrpcService(interceptors = { LogInterceptor.class })
@Slf4j
public class GreetingService extends GreeterGrpc.GreeterImplBase {
	
	@Override
	public void sayHello(GreeterOuterClass.HelloRequest request,
			StreamObserver<GreeterOuterClass.HelloReply> responseObserver) {
		
		log.info(request.getName());
		final GreeterOuterClass.HelloReply.Builder replyBuilder = GreeterOuterClass.HelloReply.newBuilder()
				.setMessage("Hello " + request.getName());
		responseObserver.onNext(replyBuilder.build());
		responseObserver.onCompleted();
	}
}
