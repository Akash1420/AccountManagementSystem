package com.barclays.AccountManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.AccountManagementSystem.Entities.AccountTransaction;
import com.barclays.AccountManagementSystem.Services.AccountTransactionService;

@RestController
public class AccountTransactionController {

	@Autowired
	private AccountTransactionService accTr;
	
	
	@GetMapping("/transaction/{acc}")
	public List<AccountTransaction> getAll(@PathVariable long acc){
		return accTr.getByAccount(acc);
	}
	
	@GetMapping("/transaction/ministatement/{acc}")
	public List<AccountTransaction> getMiniStatement(@PathVariable long acc){
		return accTr.getMiniStatementByAccount(acc);
	}
	
	
	
	@PostMapping("/transaction/credit")
	public AccountTransaction creditTransaction(@RequestBody AccountTransaction account) {
		
		return accTr.credit(account);
		
	}
	
	@PostMapping("/transaction/debit")
	public AccountTransaction debitTransaction(@RequestBody AccountTransaction account) {
		return accTr.debit(account);
		
	}
	@PostMapping("/transaction/withdrawl")
	public AccountTransaction withdrawlTransaction(@RequestBody AccountTransaction account) {
		return accTr.debit(account);
		
	}
	
	
}
