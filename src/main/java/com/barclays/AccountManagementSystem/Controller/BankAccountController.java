package com.barclays.AccountManagementSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.AccountManagementSystem.Entities.BankAccount;
import com.barclays.AccountManagementSystem.Services.AccountTransactionService;
import com.barclays.AccountManagementSystem.Services.BankAccountService;

@RestController
public class BankAccountController {
	
	@Autowired
	BankAccountService accServ;
	
	@GetMapping("/balance/{account}")
	public BankAccount showBalance(@PathVariable long account) {
		return accServ.showBalance(account).get();
	}
}
