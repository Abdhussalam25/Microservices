package com.salambasha.rest.webservices.user;

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

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	
	
	//GET/users
	//retreive all users
	@GetMapping("/users")
	public List<User> findAllUsers(){
		
		List<User> users = service.findAll();
		return users;
		
	}
	
	
	
	// retreive a specific user
	//GET/users/{id}
	@GetMapping("/users/{id}")
	public User findSingleUser(@PathVariable Integer id) {
		
		User user = service.findOne(id);
		
		return user;
		
	}
	
//	@PostMapping("/users")
//	public void saveUSer(@RequestBody User user) {
//		
//	
//		service.saveUser(user);	
//	}
//	
	//save a user
	//POST  /users
	
	// Done by me it is working fine and giving the uri in proper manner but the created is not given
	// it is only http success code 200 is returning.
//	@PostMapping("/users")
//	public String creteUser(@RequestBody User user) {
//		
//		User savedUser = service.saveUser(user);
//		int id = savedUser.getId();
//		
//		return "redirect:/users/{"+id+"}";
//		
//	}
	
	
	@PostMapping("/users" )
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		
		User savedUser = service.saveUser(user);
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
						
		return ResponseEntity.created(location).build();
		
		
	}
	

}
