package com.grpc.service.support;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Service;

import io.grpc.ServerInterceptor;

/**
 * Indicates that an annotated class is a "gRPC Service".
 *  
 * <p>This annotation serves as a specialization of {@link Service @Service},
 * allowing for implementation classes to be autodetected through classpath scanning.
 * 
 * @author harshbhavsar
 * @since 1.0.0
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface GrpcService {

	Class<? extends ServerInterceptor>[] interceptors() default {};

	boolean applyGlobalInterceptors() default true;
	
	/**
	 * The value may indicate a suggestion for a logical component name,
	 * to be turned into a Spring bean in case of an autodetected component.
	 * @return the suggested component name, if any
	 */
	String value() default "";
}