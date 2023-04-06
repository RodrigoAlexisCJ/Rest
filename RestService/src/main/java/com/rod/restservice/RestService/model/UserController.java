package com.rod.restservice.RestService.model;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@GetMapping(value = "/user/{id}", headers = "version=1", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<User> getUserV1(@PathVariable int id) {
		User userFound = service.getUser(id);
		if (userFound == null) {
			throw new UserNotFoundException("User not found id = " + id);
		} else {
			return new ResponseEntity<>(userFound,HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/user/{id}", headers = "version=2", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserV2(@PathVariable int id) {
		User userFound = service.getUser(id);
		if (userFound == null) {
			throw new UserNotFoundException("User not found id = " + id);
		} else {
			return new ResponseEntity<>(userFound,HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/users", consumes = "*/*")
//	@PostMapping(value = "/users", consumes = MediaType.APPLICATION_XML_VALUE)
//	@PostMapping(value = "/users")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user) {
		User savedUser = service.createUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
