package com.barclays.AccountManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.AccountManagementSystem.Entities.User;
import com.barclays.AccountManagementSystem.Repositories.UserRepository;
import com.barclays.AccountManagementSystem.Services.UserService;


@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("/user")
	public List<User> getAll(){
		return userService.getAll();
	}
	
	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

}
