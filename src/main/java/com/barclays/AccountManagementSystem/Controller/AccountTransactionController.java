package com.barclays.AccountManagementSystem.Controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.AccountManagementSystem.Entities.AccountTransaction;
import com.barclays.AccountManagementSystem.Repositories.AccountTransactionRepository;
import com.barclays.AccountManagementSystem.Services.AccountTransactionService;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class AccountTransactionController {

	@Autowired
	private AccountTransactionService accTr;
	
	@Autowired
	private AccountTransactionRepository accountTrRepo;
	
	
	@GetMapping("/transaction/{acc}")
	public List<AccountTransaction> getAll(@PathVariable long acc){
		return accTr.getByAccount(acc);
	}
	
	@GetMapping("/transaction/ministatement/{acc}")
	public List<AccountTransaction> getMiniStatement(@PathVariable long acc){
		return accTr.getMiniStatementByAccount(acc);
	}
	
	@PostMapping("/dateinbetween/{acc}")
	public List<AccountTransaction> getDatefrom(@PathVariable long acc, @RequestBody JsonNode payload){
		String startDate=payload.get("startDate").toString();
		String endDate=payload.get("endDate").toString();
		
		try {
		return accTr.getByDate(startDate,endDate,acc);
		}
		catch (Exception e){
			return null; 
		}
	}
	
	
	
	@PostMapping("/transaction/transerfer")
	public AccountTransaction transferTransaction(@RequestBody AccountTransaction account) {
		Random rand =new Random();
		long referenceId =rand.nextLong((long)1e10, (long)1e11);
//		Optional<AccountTransaction> actTr= accountTrRepo.findById(transacId);
//		while(actTr.get()!=null) {
//			 transacId =rand.nextLong((long)1e10, (long)1e11);
//			 actTr= accountTrRepo.findById(transacId);
//		}
	
		account.setReferenceNumber(referenceId+"");
		 accTr.credit(account);
		 accTr.debit(account);
		 return account;
		
	}
	
	
	@PostMapping("/transaction/withdrawl")
	public AccountTransaction withdrawlTransaction(@RequestBody AccountTransaction account) {
		return accTr.withdrawl(account);
		
	}
	
	
	
	
}
