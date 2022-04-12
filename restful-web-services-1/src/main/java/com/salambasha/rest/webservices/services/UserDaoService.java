package com.salambasha.rest.webservices.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.salambasha.rest.webservices.model.User;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	
	static {
		
		users.add(new User(1, "Adam" , new Date()));
		users.add(new User(2, "Eve" , new Date()));
		users.add(new User(3, "Jack" , new Date()));
		
	}
	
	int userCount = users.size();
	public List<User> findAll(){
		
		return users;
	}
	
	
	public User save(User user) {
		 
		 if(user.getId() == null) {
			
			user.setId(++userCount);
		 }
		 users.add(user);
		
		return user;
	}
	
	
	public User findOne(int id) {
		User user2 = null;
		
		for(User user : users) {
			if(user.getId() == id) {
				
				user2 = user;
				break;
			}
		}
		
		return user2;
		
	}
	
	
	public User  deleteById(int id) {
		User user2 = null;
		// we cannot delete in between for loop
		Iterator<User> iterator = users.iterator();
		
		while(iterator.hasNext()) {
			
			User user = iterator.next();
			
			if(user.getId() == id) {
				iterator.remove();
				user2 = user;
				break;
			}
		}
		
		return user2;
		
	}
	

	
}
