package com.grpc.service.support;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Service;

import io.grpc.ServerInterceptor;

/**
 * annotation class to register service as GrpcService
 * 
 * @author harshbhavsar
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface GrpcService {

	Class<? extends ServerInterceptor>[] interceptors() default {};

	boolean applyGlobalInterceptors() default true;
}