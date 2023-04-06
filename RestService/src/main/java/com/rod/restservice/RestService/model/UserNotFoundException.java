package com.rod.restservice.RestService.model;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException(String message){
		super(message);
	}
}
