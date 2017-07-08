package com.grpc.service.services;

import org.springframework.stereotype.Component;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import lombok.extern.slf4j.Slf4j;

/**
 * Log interceptor class for server calls
 * 
 * @author harshbhavsar
 *
 */
@Slf4j
@Component
public class LogInterceptor implements ServerInterceptor {


	@Override
	public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers,
			ServerCallHandler<ReqT, RespT> next) {
		System.out.println(call.getMethodDescriptor().getFullMethodName());
		log.info("Full Method name {}", call.getMethodDescriptor().getFullMethodName());
		return next.startCall(call, headers);
	}
}