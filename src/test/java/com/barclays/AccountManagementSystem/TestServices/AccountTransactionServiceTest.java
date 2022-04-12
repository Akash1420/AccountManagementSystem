package com.barclays.AccountManagementSystem.TestServices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.barclays.AccountManagementSystem.Entities.AccountTransaction;
import com.barclays.AccountManagementSystem.Entities.BankAccount;
import com.barclays.AccountManagementSystem.Entities.Role;
import com.barclays.AccountManagementSystem.Entities.User;
import com.barclays.AccountManagementSystem.Repositories.AccountTransactionRepository;
import com.barclays.AccountManagementSystem.Repositories.BankAccountRepository;
import com.barclays.AccountManagementSystem.Services.AccountTransactionService;

@ExtendWith(MockitoExtension.class)
public class AccountTransactionServiceTest {
	
	@InjectMocks
	private AccountTransactionService accountTrasactionService;

	@Mock
	private AccountTransactionRepository accountTrRepo;
	
	@Mock
	private BankAccountRepository bankAccountRepo;
	
	@Test
	public void creditTest() {
		Date date = new Date();
		AccountTransaction transaction = new AccountTransaction(156384, "asa55645a", date, "credit", "online", 1000, 154684312, 15613885);
		BankAccount bankAcc = new BankAccount();
		Optional<BankAccount> acc = Optional.of(bankAcc);
		List<AccountTransaction> transList = new ArrayList<AccountTransaction>();
		transList.add(transaction);
		
		when(accountTrRepo.findBydateTimeAndAccountNumber(any(Date.class), eq(transaction.getAccountNumber()))).thenReturn(transList);
		when(accountTrRepo.save(any(AccountTransaction.class))).thenReturn(null);
		when(bankAccountRepo.findById(transaction.getAccountNumber())).thenReturn(acc);
		when(bankAccountRepo.save(any(BankAccount.class))).thenReturn(null);
		
		AccountTransaction res = accountTrasactionService.credit(transaction);
		assertEquals(transaction.getTransactionId(), res.getTransactionId());
		
	}
	
	@Test
	public void debitTest() {
		Date date = new Date();
		AccountTransaction transaction = new AccountTransaction(156384, "asa55645a", date, "debit", "online", 1000, 154684312, 4851383);
		BankAccount bankAcc = new BankAccount();
		Optional<BankAccount> acc = Optional.of(bankAcc);
		List<AccountTransaction> transList = new ArrayList<AccountTransaction>();
		transList.add(transaction);
		
		when(accountTrRepo.findBydateTimeAndAccountNumber(any(Date.class), eq(transaction.getAccountNumber()))).thenReturn(transList);
		when(accountTrRepo.save(any(AccountTransaction.class))).thenReturn(null);
		when(bankAccountRepo.findById(transaction.getAccountNumber())).thenReturn(acc);
		when(bankAccountRepo.save(any(BankAccount.class))).thenReturn(null);
		
		AccountTransaction res = accountTrasactionService.credit(transaction);
		assertEquals(transaction.getTransactionId(), res.getTransactionId());
		
	}
	
	@Test
	public void withdrawlTest() {
		Date date = new Date();
		AccountTransaction transaction = new AccountTransaction(156384, "asa55645a", date, "withdrawl", "cash", 1000, 154684312,8138435);
		BankAccount bankAcc = new BankAccount();
		Optional<BankAccount> acc = Optional.of(bankAcc);
		List<AccountTransaction> transList = new ArrayList<AccountTransaction>();
		transList.add(transaction);
		
		when(accountTrRepo.findBydateTimeAndAccountNumber(any(Date.class), eq(transaction.getAccountNumber()))).thenReturn(transList);
		when(accountTrRepo.save(any(AccountTransaction.class))).thenReturn(null);
		when(bankAccountRepo.findById(transaction.getAccountNumber())).thenReturn(acc);
		when(bankAccountRepo.save(any(BankAccount.class))).thenReturn(null);
		
		AccountTransaction res = accountTrasactionService.credit(transaction);
		assertEquals(transaction.getTransactionId(), res.getTransactionId());
		
	}
	
	@Test
	public void getByAccountTest() {
		Date date = new Date();
		AccountTransaction transaction = new AccountTransaction(156384, "asa55645a", date, "credit", "online", 1000, 154684312,485138);
		List<AccountTransaction> transList = new ArrayList<AccountTransaction>();
		transList.add(transaction);
		
		when(accountTrRepo.findByAccountNumber(transaction.getAccountNumber())).thenReturn(transList);
		
		List<AccountTransaction> res = accountTrasactionService.getByAccount(transaction.getAccountNumber());
		assertEquals(transList.size(), res.size());
	}
	
	@Test
	public void getMiniStatementByAccountTest() {
		Date date = new Date();
		List<AccountTransaction> transList = new ArrayList<AccountTransaction>();
		AccountTransaction transaction = new AccountTransaction(156384, "asa55645a", date, "", "", 1000, 154684312, 4813852);
		transaction.setTransactionType("credit");
		transaction.setSubType("online");
		transList.add(transaction);
		
		date = new Date();
		transaction = new AccountTransaction(156385, "asa55645a", date, "", "", 1000, 154684312, 4813852);
		transaction.setTransactionType("credit");
		transaction.setSubType("online");
		transList.add(transaction);
		
		date = new Date();
		transaction = new AccountTransaction(156386, "asa55645a", date, "", "", 1000, 154684312, 4813852);
		transaction.setTransactionType("credit");
		transaction.setSubType("online");
		transList.add(transaction);
		
		date = new Date();
		transaction = new AccountTransaction(156387, "asa55645a", date, "", "", 1000, 154684312, 4813852);
		transaction.setTransactionType("credit");
		transaction.setSubType("online");
		transList.add(transaction);
		
		date = new Date();
		transaction = new AccountTransaction(156388, "asa55645a", date, "", "", 1000, 154684312, 4813852);
		transaction.setTransactionType("credit");
		transaction.setSubType("online");
		transList.add(transaction);
		
		when(accountTrRepo.findByAccountNumber(transaction.getAccountNumber())).thenReturn(transList);
		
		List<AccountTransaction> res = accountTrasactionService.getMiniStatementByAccount(transaction.getAccountNumber());
		assertEquals(transList.size(), res.size());
	}
}
