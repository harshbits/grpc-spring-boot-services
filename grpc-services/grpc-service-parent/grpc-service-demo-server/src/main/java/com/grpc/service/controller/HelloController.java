package com.grpc.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/hello")
public class HelloController {

//	@Autowired
//	private GreetingService greetingService;
//	
//	@ResponseBody
//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseEntity<String> sayHello() {
//		return new ResponseEntity<>("Welcome gRPC call!", HttpStatus.OK);
//	}
	
	
	  @Autowired
	    HttpMessageConverters httpMessageConverters;

//	    @PostMapping("book")
//	    public List<Book> createBooks(@RequestBody List<Book> books) {
//
//	        books.forEach(book -> {
//	            book.setISBN(BookUtil.generateISBN());
//	        });
//
//	        return books;
//	    }

	    @GetMapping()
	    public void test() {
	        httpMessageConverters.getConverters().forEach(System.out::println);
	    }

}
