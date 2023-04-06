package com.rod.restservice.RestService.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> getAll() {
		return repository.findAll();
	}
	
	public User getUser(int id) {
		return repository.findUserById(id);
	}
	
	public User createUser(User user) {
		return repository.save(user);
	}
	
	public void deleteUser(int id) {
		repository.deleteById(id);
	}
}
