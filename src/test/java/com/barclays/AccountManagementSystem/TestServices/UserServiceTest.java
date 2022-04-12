package com.barclays.AccountManagementSystem.TestServices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.barclays.AccountManagementSystem.Entities.BankAccount;
import com.barclays.AccountManagementSystem.Entities.User;
import com.barclays.AccountManagementSystem.Repositories.BankAccountRepository;
import com.barclays.AccountManagementSystem.Repositories.UserRepository;
import com.barclays.AccountManagementSystem.Services.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private BankAccountRepository bankRepository;
	
	@Test
	public void updatePasswordTest() {
		User user = new User(123456,"","12345",1,true);
		Optional<User> users = Optional.of(user);
		
		when(userRepository.findById(user.getUserID())).thenReturn(users);
		when(userRepository.save(any(User.class))).thenReturn(null);
		
		User res = userService.updatePassword(user.getUserID(), "qwerty");
		assertEquals("qwerty", res.getPassword());
	}
	
	@Test
	public void userLoginTest1() {
		User user = new User(123456,"","12345",1,true);
		List<BankAccount> account = new ArrayList<>();
		account.add(new BankAccount(user.getCustomerID(), 0));
		account.add(new BankAccount(user.getCustomerID(), 0));
		
		when(userRepository.findByCustomerID(user.getCustomerID())).thenReturn(user);
		when(userRepository.findByCustomerIDAndTempPassword(user.getCustomerID(), user.getTempPassword())).thenReturn(user);
		when(bankRepository.findByCustomerId(user.getCustomerID())).thenReturn(account);
		
		List<BankAccount> res = userService.userlogin(user.getCustomerID(), user.getTempPassword());
		assertEquals(account.size(), res.size());
	}
	
	@Test
	public void userLoginTest2() {
		User user = new User(123456,"qwerty","12345",1,false);
		List<BankAccount> account = new ArrayList<>();
		account.add(new BankAccount(user.getCustomerID(), 0));
		account.add(new BankAccount(user.getCustomerID(), 0));
		
		when(userRepository.findByCustomerID(user.getCustomerID())).thenReturn(user);
		when(userRepository.findByCustomerIDAndPassword(user.getCustomerID(), user.getPassword())).thenReturn(user);
		when(bankRepository.findByCustomerId(user.getCustomerID())).thenReturn(account);
		
		List<BankAccount> res = userService.userlogin(user.getCustomerID(), user.getPassword());
		assertEquals(account.size(), res.size());
	}
	
	@Test
	public void userLoginTest3() {
		User user = new User(123456,"qwerty","12345",1,false);
		List<BankAccount> account = new ArrayList<>();
		
		when(userRepository.findByCustomerID(user.getCustomerID())).thenReturn(user);
		when(userRepository.findByCustomerIDAndPassword(user.getCustomerID(), user.getPassword())).thenReturn(null);
		
		List<BankAccount> res = userService.userlogin(user.getCustomerID(), user.getPassword());
		assertEquals(account.size(), res.size());
	}
}
