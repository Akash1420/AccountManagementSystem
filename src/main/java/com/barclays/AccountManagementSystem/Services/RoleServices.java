package com.barclays.AccountManagementSystem.Services;

import java.util.Random;

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
		Random rand =new Random();
		if(bankCustomerRepo.findByPanCard(userDetails.getPanCard())==null) {
			
			long customerId =rand.nextLong((long)1e5, (long)1e6);
			userDetails.setCustomerID(customerId);
			bankCustomerRepo.save(userDetails);
			
			bankCus = bankCustomerRepo.findByPanCard(userDetails.getPanCard());
			User user=new User(bankCus.getCustomerID(),"","12345",1,true);
			
			userRepo.save(user);
			
			Role role =new Role(userDetails.getName());
			roleRepo.save(role);
		}
		
		bankCus = bankCustomerRepo.findByPanCard(userDetails.getPanCard());
		
		BankAccount bankAcc = new BankAccount(bankCus.getCustomerID(),10000);
		long Accountnumber =rand.nextLong((long)1e9, (long)1e10);
		bankAcc.setAccountNumber(Accountnumber);
		bankAccountRepo.save(bankAcc);
		return userDetails;
		
		
	}

	
}
