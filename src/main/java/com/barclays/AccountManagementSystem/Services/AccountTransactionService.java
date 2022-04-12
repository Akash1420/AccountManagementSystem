package com.barclays.AccountManagementSystem.Services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import  java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.barclays.AccountManagementSystem.Entities.AccountTransaction;
import com.barclays.AccountManagementSystem.Entities.BankAccount;
import com.barclays.AccountManagementSystem.Repositories.AccountTransactionRepository;
import com.barclays.AccountManagementSystem.Repositories.BankAccountRepository;

@Repository
public class AccountTransactionService {
	
	@Autowired
	private AccountTransactionRepository accountTrRepo;
	
	@Autowired
	private BankAccountRepository bankAccountRepo;
	
	public AccountTransaction credit(AccountTransaction transaction) {
		Date currentDate = Calendar.getInstance().getTime();
		transaction.setDateTime(currentDate);
		
		accountTrRepo.save(transaction);
		
		
		Optional<BankAccount> banAcc =  bankAccountRepo.findById(transaction.getAccountNumber()) ;
		BankAccount acc =banAcc.get();
		acc.setBalance(acc.getBalance()+transaction.getBalance());
		bankAccountRepo.save(acc);
		
		return transaction;
	}
	
	public AccountTransaction debit(AccountTransaction transaction) {
		Date currentDate = Calendar.getInstance().getTime();
		transaction.setDateTime(currentDate);
		accountTrRepo.save(transaction);
		
		Optional<BankAccount> banAcc =  bankAccountRepo.findById(transaction.getAccountNumber()) ;
		BankAccount acc =banAcc.get();
		acc.setBalance(acc.getBalance()-transaction.getBalance());
		bankAccountRepo.save(acc);
		
		return transaction;
	}
	
	public AccountTransaction withdrawl(AccountTransaction transaction) {
		Date currentDate = Calendar.getInstance().getTime();
		transaction.setDateTime(currentDate);
		accountTrRepo.save(transaction);
		
	

		Optional<BankAccount> banAcc = bankAccountRepo.findById(transaction.getAccountNumber());
		BankAccount acc = banAcc.get();
		acc.setBalance(acc.getBalance() - transaction.getBalance());
		bankAccountRepo.save(acc);

		return transaction;
	}
	
	public List<AccountTransaction> getByAccount(long accountNo) {
		return accountTrRepo.findByAccountNumber(accountNo);
	}
	
	public List<AccountTransaction> getMiniStatementByAccount(long accountNo) {
		List<AccountTransaction> accountTrans =accountTrRepo.findByAccountNumber(accountNo);
		
		Collections.sort(accountTrans, new Comparator<AccountTransaction>() {
			
			public int compare(AccountTransaction o1, AccountTransaction o2) {
				return o2.getDateTime().toString().compareTo(o1.getDateTime().toString());
			}
		});
		
		List<AccountTransaction> accountTransacn =new ArrayList();
		int size = accountTrans.size();
		
		if(size<=5) {
			return accountTrans;
		}
		else {
			for(int i=0;i<5;i++) {
				accountTransacn.add(accountTrans.get(i));
			}
			
			return accountTransacn;
		}
	}
	
	

}
