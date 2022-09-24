package com.in28minutes.rest.webservices.helloWorld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


//Step 02 - Creating a Hello World REST API with Spring Boot
@RestController
public class HelloWorld {
	/*
	 * private static final Logger log =
	 * LoggerFactory.getLogger(HelloWorld.class.getSimpleName());
	 */
	 
	@GetMapping(path ="/hello-world")
	public String helloWorld() {
		/* log.info("Called helloWorld()"); */
		return "hello world";
		
	}	
}
