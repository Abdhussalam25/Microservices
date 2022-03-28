package com.salambasha.rest.webservices.Helloword;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.salambasha.rest.webservices.model.User;
import com.salambasha.rest.webservices.services.UserDaoService;

@RestController
public class UserRestController {
	
	@Autowired
	UserDaoService service;
	
	//Reterieve all users
	@GetMapping("/users")
	public List<User> allUsers(){
		
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public User findById(@PathVariable int id) {
		
		return service.findOne(id);
	}
	
	//input - details of user
	// output - Created $ return the created uri
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		
	User savedUser = 	service.save(user);
	URI location = ServletUriComponentsBuilder
	.fromCurrentRequest()
	.path("/{id}")
	.buildAndExpand(savedUser.getId()).toUri();
	
	return ResponseEntity.created(location).build();	
	}
	
	
	
	
}
