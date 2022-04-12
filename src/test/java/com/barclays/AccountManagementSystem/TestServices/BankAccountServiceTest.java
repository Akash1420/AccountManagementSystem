package com.barclays.AccountManagementSystem.TestServices;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.barclays.AccountManagementSystem.Entities.BankAccount;
import com.barclays.AccountManagementSystem.Repositories.BankAccountRepository;
import com.barclays.AccountManagementSystem.Services.BankAccountService;

@ExtendWith(MockitoExtension.class)
public class BankAccountServiceTest {
	
	@InjectMocks
	private BankAccountService bankAccountService;

	@Mock
	private BankAccountRepository bankAccRepo;
	
	@Test
	public void showBalanceTest(){
		BankAccount account = new BankAccount(1, 0);
		Optional<BankAccount> accounts = Optional.of(account);
		
		when(bankAccRepo.findById(account.getAccountNumber())).thenReturn(accounts);
		
		Optional<BankAccount> res = bankAccountService.showBalance(account.getAccountNumber());
		assertTrue(res.isPresent());
	}
}
