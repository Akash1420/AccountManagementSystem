package com.barclays.AccountManagementSystem.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.barclays.AccountManagementSystem.Entities.BankAccount;
import com.barclays.AccountManagementSystem.Repositories.BankAccountRepository;

@Repository
public class BankAccountService {
	
	@Autowired
	private BankAccountRepository bankAccRepo;
	
	
	public Optional<BankAccount> showBalance(long accountNo) {
		
		return bankAccRepo.findById(accountNo);
		
	}
	

}
