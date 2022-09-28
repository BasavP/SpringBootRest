package com.in28minutes.rest.webservices.helloWorld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;


//Step 02 - Creating a Hello World REST API with Spring Boot
@RestController
public class HelloWorld {

	MessageSource messageSource;

	HelloWorld(MessageSource messageSource){

		this.messageSource=messageSource;
	}
	 
	@GetMapping("/hello-world")
	public String helloWorld() {

		return "hello world";
		
	}	
	
	
	//Step 03 - Enhancing the Hello World REST API to return a Bean
	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {

		return new HelloWorldBean("Hello world Beans");
	}


	//Introduced PathVariable
	@GetMapping("/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable("name") String names) {
		/* log.info("Called helloWorld()"); */
		return new HelloWorldBean("Hello world "+ names);
	}


	@GetMapping("/hello-world-internationalized")
	public String helloWorldInternationalized() {

		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message" , null ,
				"DefaultMessage",locale);
	}



}
