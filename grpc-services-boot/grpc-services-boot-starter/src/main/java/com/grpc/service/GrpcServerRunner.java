/**
 * Copyright (c) 2017 Harsh Bhavsar.  All rights reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 		
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.grpc.service;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.type.StandardMethodMetadata;

import com.grpc.service.support.GrpcGlobalInterceptor;
import com.grpc.service.support.GrpcService;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptor;
import io.grpc.ServerInterceptors;
import io.grpc.ServerServiceDefinition;
import lombok.extern.slf4j.Slf4j;

/**
 * This is a gRPC Server Runner class 
 * 
 * @author harshbhavsar
 * @since 1.0.0
 *
 */
@Slf4j
public class GrpcServerRunner implements CommandLineRunner, DisposableBean {

	@Autowired
	private AbstractApplicationContext applicationContext;

	@Autowired
	private GrpcServerProperties grpcServerProperties;

	private GrpcServerBuilderConfigurer configurer;

	private Server server;

	public GrpcServerRunner(GrpcServerBuilderConfigurer configurer) {
		this.configurer = configurer;
	}

	/**
	 * Running Server
	 * @throws Exception 
	 * 
	 */
	@Override
	public void run(String... args) throws Exception  {
		log.info("Starting gRPC Server ...");

		Collection<ServerInterceptor> globalInterceptors = getBeanNamesByTypeWithAnnotation(GrpcGlobalInterceptor.class,
				ServerInterceptor.class)
						.map(name -> applicationContext.getBeanFactory().getBean(name, ServerInterceptor.class))
						.collect(Collectors.toList());

		final ServerBuilder<?> serverBuilder = ServerBuilder.forPort(grpcServerProperties.getPort());

		// find and register all GrpcService-enabled beans
		getBeanNamesByTypeWithAnnotation(GrpcService.class, BindableService.class).forEach(name -> {
			BindableService srv = applicationContext.getBeanFactory().getBean(name, BindableService.class);
			ServerServiceDefinition serviceDefinition = srv.bindService();
			GrpcService gRpcServiceAnn = applicationContext.findAnnotationOnBean(name, GrpcService.class);
			serviceDefinition = bindInterceptors(serviceDefinition, gRpcServiceAnn, globalInterceptors);
			serverBuilder.addService(serviceDefinition);
			log.info("'{}' service has been registered.", srv.getClass().getName());

		});

		configurer.configure(serverBuilder);
		server = serverBuilder.build().start();
		log.info("gRPC Server started on port {}.", grpcServerProperties.getPort());
		startDaemonAwaitThread();

	}

	/**
	 * Method binds server service to gRPC services
	 * 
	 * @param serviceDefinition
	 * @param gRpcService
	 * @param globalInterceptors
	 * @return
	 */
	private ServerServiceDefinition bindInterceptors(ServerServiceDefinition serviceDefinition, GrpcService gRpcService,
			Collection<ServerInterceptor> globalInterceptors) {

		Stream<? extends ServerInterceptor> privateInterceptors = Stream.of(gRpcService.interceptors())
				.map(interceptorClass -> {
					try {
						return 0 < applicationContext.getBeanNamesForType(interceptorClass).length
								? applicationContext.getBean(interceptorClass) : interceptorClass.newInstance();
					} catch (Exception e) {
						throw new BeanCreationException("Failed to create interceptor instance.", e);
					}
				});

		List<ServerInterceptor> interceptors = Stream
				.concat(gRpcService.applyGlobalInterceptors() ? globalInterceptors.stream() : Stream.empty(),
						privateInterceptors)
				.distinct().collect(Collectors.toList());
		return ServerInterceptors.intercept(serviceDefinition, interceptors);
	}

	/**
	 * 
	 */
	private void startDaemonAwaitThread() {
		Thread awaitThread = new Thread() {
			@Override
			public void run() {
				try {
					GrpcServerRunner.this.server.awaitTermination();
				} catch (InterruptedException e) {
					log.error("gRPC server stopped.", e);
				}
			}

		};
		awaitThread.setDaemon(false);
		awaitThread.start();
	}

	/**
	 * 
	 */
	@Override
	public void destroy() throws Exception {
		log.info("Shutting down gRPC server ...");
		Optional.ofNullable(server).ifPresent(Server::shutdown);
		log.info("gRPC server stopped.");
	}

	/**
	 * 
	 * @param annotationType
	 * @param beanType
	 * @return
	 * <p>
	 * @throws Exception
	 */
	private <T> Stream<String> getBeanNamesByTypeWithAnnotation(Class<? extends Annotation> annotationType,
			Class<T> beanType) throws Exception {

		return Stream.of(applicationContext.getBeanNamesForType(beanType)).filter(name -> {
			final BeanDefinition beanDefinition = applicationContext.getBeanFactory().getBeanDefinition(name);
			final Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(annotationType);

			if (!beansWithAnnotation.isEmpty()) {
				return beansWithAnnotation.containsKey(name);
			} else if (beanDefinition.getSource() instanceof StandardMethodMetadata) {
				StandardMethodMetadata metadata = (StandardMethodMetadata) beanDefinition.getSource();
				return metadata.isAnnotated(annotationType.getName());
			}

			return false;
		});
	}

}