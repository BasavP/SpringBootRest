package com.in28minutes.rest.webservices.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) 
			throws Exception {
		
		ErrorDetails error =  new ErrorDetails
				(LocalDateTime.now(), ex.getMessage(),request.getDescription(false));
		
		return new ResponseEntity(error,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		
		ErrorDetails error =  new ErrorDetails
				(LocalDateTime.now(), ex.getMessage(),request.getDescription(false));
		
		return new ResponseEntity(error,HttpStatus.BAD_REQUEST);

	}
	//some comment
	public CustomizedResponseEntityExceptionHandler() {
		// TODO Auto-generated constructor stub
	}

}
