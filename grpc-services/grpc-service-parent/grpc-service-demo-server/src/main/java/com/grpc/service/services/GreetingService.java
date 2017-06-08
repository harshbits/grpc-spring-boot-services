package com.grpc.service.services;

import com.copart.grpc.service.gen.GreeterGrpc;
import com.copart.grpc.service.gen.GreeterOuterClass;
import com.grpc.service.support.GrpcService;

import io.grpc.stub.StreamObserver;

@GrpcService(interceptors = { LogInterceptor.class })
public class GreetingService extends GreeterGrpc.GreeterImplBase {
	
	@Override
	public void sayHello(GreeterOuterClass.HelloRequest request,
			StreamObserver<GreeterOuterClass.HelloReply> responseObserver) {
		final GreeterOuterClass.HelloReply.Builder replyBuilder = GreeterOuterClass.HelloReply.newBuilder()
				.setMessage("Hello " + request.getName());
		responseObserver.onNext(replyBuilder.build());
		responseObserver.onCompleted();
	}
}
