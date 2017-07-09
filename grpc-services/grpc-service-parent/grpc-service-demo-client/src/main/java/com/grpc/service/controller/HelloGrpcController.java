package com.grpc.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.grpc.service.gen.GreeterOuterClass.HelloReply;
import com.grpc.service.services.GreetingServiceGrpcClient;

/**
 * Hello Greeting gRPC controller
 * 
 * @author harshbhavsar
 *
 */
@RestController
public class HelloGrpcController {

	@Autowired
	private GreetingServiceGrpcClient greetingServiceGrpcClient;

	@RequestMapping(value = "/hello_grpc", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> test(
			@RequestParam(value = "compact", required = false, defaultValue = "false") boolean compact) {

		HelloReply response = greetingServiceGrpcClient.sayHello();

		if (compact) {
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} else {

			String jsonString = "";
			try {
				jsonString = JsonFormat.printer().print(response);
			} catch (InvalidProtocolBufferException e) {
				e.printStackTrace();
			}

			return new ResponseEntity<>(jsonString, HttpStatus.OK);
		}
	}
}
