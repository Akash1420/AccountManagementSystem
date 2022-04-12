package com.barclays.AccountManagementSystem.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.barclays.AccountManagementSystem.Entities.BankAccount;
import com.barclays.AccountManagementSystem.Entities.User;
import com.barclays.AccountManagementSystem.Repositories.BankAccountRepository;
import com.barclays.AccountManagementSystem.Repositories.UserRepository;

@Repository
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BankAccountRepository bankRepository;
		
		public User updatePassword(long id, String password ) {
			Optional<User> user = userRepository.findById(id);
			
			User oldUser= user.get();
			oldUser.setPassword(password);
			oldUser.setTempPassValid(false);
			 userRepository.save(oldUser);
			 return oldUser;
		}
		
		public List<BankAccount> userlogin(long customerId, String password){
		
			User user;
			List<BankAccount> account=new ArrayList<>();
			if(userRepository.findByCustomerID(customerId).isTempPassValid()) {
				user=userRepository.findByCustomerIDAndTempPassword(customerId, password);
				System.out.println();
				System.out.println(user + " "+1);
				System.out.println();
			}
			else {
				user=userRepository.findByCustomerIDAndPassword(customerId, password);
				System.out.println();
				System.out.println(user + " "+ 2);
				System.out.println();
			}
			
			if(user!=null) {
				
				return bankRepository.findByCustomerId(customerId);
			}
			else {
				return account;
			}
			
			
		}

}
