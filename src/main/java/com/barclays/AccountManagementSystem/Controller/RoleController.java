package com.barclays.AccountManagementSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.AccountManagementSystem.Entities.BankCustomer;
import com.barclays.AccountManagementSystem.Entities.Role;
import com.barclays.AccountManagementSystem.Services.RoleServices;

@RestController
public class RoleController {
	@Autowired
	private RoleServices roleService;
	
	@PostMapping("/addmanager")
	public Role createManager(@RequestBody Role role)	{
		System.out.println(role.getName());
		System.out.println(role.getName());
		System.out.println(role.getName());
		return roleService.createManager(role);
		
	}
	
	@PostMapping("/addUser")
	public BankCustomer createUser(@RequestBody BankCustomer userDetails)	{
		
		return roleService.createUser(userDetails);
		
	}
	
}
