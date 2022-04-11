package com.barclays.AccountManagementSystem.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.barclays.AccountManagementSystem.Entities.User;
import com.barclays.AccountManagementSystem.Repositories.UserRepository;

@Repository
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAll(){
		  return userRepository.findAll();	
		}
		
		public User saveUser(User user) {
			return userRepository.save(user);
		}

}
