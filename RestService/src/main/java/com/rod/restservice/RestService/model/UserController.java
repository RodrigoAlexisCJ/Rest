package com.rod.restservice.RestService.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	
	// Versioning Headers
	@GetMapping(value = "/users", headers = "version=1", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<User>> getAllUsersV1() {
		return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/users", headers = "version=2")
	public ResponseEntity<List<User>> getAllUsersV2() {
		return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable int id){
		User userFound = service.getUser(id);
		if (userFound == null) {
			throw new UserNotFoundException("User not found id = " + id);
		} else {
			return new ResponseEntity<>(userFound,HttpStatus.OK);
		}
	}
}
