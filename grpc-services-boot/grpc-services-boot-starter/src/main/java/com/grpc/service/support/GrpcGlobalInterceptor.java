package com.grpc.service.support;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Marks a ServerInterceptor Component as global intercepter 
 * 
 * <p>This annotation serves as a specialization of {@link Service @Service},
 * 
 * @author harshbhavsar
 * @since 1.0.0
 *
 */
@Target({ElementType.TYPE,ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface GrpcGlobalInterceptor {
}
