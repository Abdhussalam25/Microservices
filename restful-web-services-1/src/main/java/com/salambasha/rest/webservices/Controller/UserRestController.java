package com.salambasha.rest.webservices.Controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.salambasha.rest.webservices.Exceptions.UserNotFoundException;
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
	public EntityModel<User> findById(@PathVariable int id) {
		User user = service.findOne(id);		
		if(user == null)
			throw new UserNotFoundException(" id - " + id + " not found");
		
		EntityModel<User> model = EntityModel.of(user);
		
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).allUsers());
		
		WebMvcLinkBuilder deleteUser = linkTo(methodOn(this.getClass()).deleteById(id));
		
		model.add(deleteUser.withRel("deleteUser"));
		
		model.add(linkToUsers.withRel("all-users"));
		
		return model;
	}
	
	@DeleteMapping("/users/{id}")
	public User deleteById(@PathVariable int id) {
		User user = service.deleteById(id);
		
		if(user == null)
			throw new UserNotFoundException(" id - " + id + " not found");
		
		return user;
	}
	
	//input - details of user
	// output - Created $ return the created uri
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		
	User savedUser = 	service.save(user);
	URI location = ServletUriComponentsBuilder
	.fromCurrentRequest()
	.path("/{id}")
	.buildAndExpand(savedUser.getId()).toUri();
	
	return ResponseEntity.created(location).build();	
	}
	
	
	
	
}
