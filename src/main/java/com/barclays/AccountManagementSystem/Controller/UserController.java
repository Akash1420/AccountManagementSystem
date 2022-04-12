package com.barclays.AccountManagementSystem.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.AccountManagementSystem.Entities.BankAccount;
import com.barclays.AccountManagementSystem.Entities.User;
import com.barclays.AccountManagementSystem.Repositories.UserRepository;
import com.barclays.AccountManagementSystem.Services.UserService;
import com.fasterxml.jackson.databind.JsonNode;


@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@GetMapping("/user")
	public List<User> getAll(){
		List<User> userList = userRepo.findAll();
		return userList;
	}
	
//	
//	@PostMapping("/user")
//	public User saveUser(@RequestBody User user) {
//		return userService.saveUser(user);
//	}
	
	@PutMapping("/user/updatepassword/{id}")
	public User updatePassword(@PathVariable long id ,@RequestBody  JsonNode payload ) {
		String password =payload.get("password").toString();
		System.out.println(password);
		return userService.updatePassword(id,password);
	}
	
	
	//modify
	
	@PostMapping("/user/login")
	public List<BankAccount> userlogin(@RequestBody JsonNode payload){
		String id= payload.get("customerId").toString();
		long customerId= Long.parseLong(id);
		String password= payload.get("password").toString();
		System.out.println();
		System.out.println(customerId + " "+ password);
		System.out.println();
		List<BankAccount> accounts=userService.userlogin((long)customerId,(String) password);
		return accounts;
	}
	
	

	
	
}
