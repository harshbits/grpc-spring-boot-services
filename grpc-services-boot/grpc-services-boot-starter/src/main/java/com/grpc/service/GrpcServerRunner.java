package com.grpc.service;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger log = LoggerFactory.getLogger(GrpcServerRunner.class);

	/**
	 * Running Server
	 * 
	 */
	@Override
	public void run(String... args) throws Exception {
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
		log.info("gRPC Server started, listening on port {}.", grpcServerProperties.getPort());
		startDaemonAwaitThread();

	}

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

	@Override
	public void destroy() throws Exception {
		log.info("Shutting down gRPC server ...");
		Optional.ofNullable(server).ifPresent(Server::shutdown);
		log.info("gRPC server stopped.");
	}

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