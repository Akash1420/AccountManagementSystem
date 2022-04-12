package com.barclays.AccountManagementSystem.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.barclays.AccountManagementSystem.Entities.BankAccount;
import com.barclays.AccountManagementSystem.Entities.BankCustomer;
import com.barclays.AccountManagementSystem.Entities.Role;
import com.barclays.AccountManagementSystem.Entities.User;
import com.barclays.AccountManagementSystem.Repositories.BankAccountRepository;
import com.barclays.AccountManagementSystem.Repositories.BankCustomerRepository;
import com.barclays.AccountManagementSystem.Repositories.RoleRepository;
import com.barclays.AccountManagementSystem.Repositories.UserRepository;

@Repository
public class RoleServices {
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private BankCustomerRepository bankCustomerRepo;
	
	@Autowired
	private BankAccountRepository bankAccountRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public Role createManager(Role role) {
		System.out.println(role.getName());
		
		
		roleRepo.save(role);
		return role;
	}
	
	public BankCustomer createUser(BankCustomer userDetails) {
		BankCustomer bankCus;
		if(bankCustomerRepo.findByPanCard(userDetails.getPanCard())==null) {
			bankCustomerRepo.save(userDetails);
			
			bankCus = bankCustomerRepo.findByPanCard(userDetails.getPanCard());
			User user=new User(bankCus.getCustomerID(),"","12345",1,true);
			
			userRepo.save(user);
			
			Role role =new Role(userDetails.getName());
			roleRepo.save(role);
		}
		
		bankCus = bankCustomerRepo.findByPanCard(userDetails.getPanCard());
		
		BankAccount bankAcc = new BankAccount(bankCus.getCustomerID(),0);
		bankAccountRepo.save(bankAcc);
		return userDetails;
		
		
	}

	
}
