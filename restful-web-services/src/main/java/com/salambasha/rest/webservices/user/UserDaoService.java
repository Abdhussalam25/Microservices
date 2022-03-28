package com.salambasha.rest.webservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	

	private static List<User> users = new ArrayList<>();
	
	private static int userCount = 3;
	
	static {
		users.add(new User(1,"Adam",new Date()));
		users.add(new User(2,"Eve",new Date()));
		users.add(new User(3,"Noah",new Date()));
		
	}
	
	//findAll();	
	
	public List<User> findAll(){
		
		return users;
	}
	
	
	//create a user;
	public User saveUser(User user) {
		//Integer id = user.getId();
		
		if(user.getId()==null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	
	//retrun a Specific user;
	public User findOne(int id) {
		
		for(User user: users) {
			if(user.getId()==id)
				return user;
		}
		
		return null;
	}
	
	
	
	//delete a user;
	public void deleteUser(int id) {
		for(User user:users) {
			if(user.getId()==id)
				users.remove(user);
		}
			
	}
	
	
	
	
	
	
	
}
