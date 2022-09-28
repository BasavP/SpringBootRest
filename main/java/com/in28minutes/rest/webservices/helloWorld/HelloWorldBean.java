package com.in28minutes.rest.webservices.helloWorld;

import com.fasterxml.jackson.annotation.JsonProperty;

//- Step 03 - Enhancing the Hello World REST API to return a Bean
public class HelloWorldBean {


	@JsonProperty("Messages")
	private String messages;

	public HelloWorldBean(String messages) {
		this.messages = messages;
	}

	//- Step 03 - Enhancing the Hello World REST API to return a Bean
	@JsonProperty("Messages")
	public String getMessage() {
		return messages;
	}

	public void setMessage(String messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [messages=" + messages + "]";
	}
}
