package com.barclays.AccountManagementSystem.TestServices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.barclays.AccountManagementSystem.Entities.BankAccount;
import com.barclays.AccountManagementSystem.Entities.BankCustomer;
import com.barclays.AccountManagementSystem.Entities.Role;
import com.barclays.AccountManagementSystem.Entities.User;
import com.barclays.AccountManagementSystem.Repositories.BankAccountRepository;
import com.barclays.AccountManagementSystem.Repositories.BankCustomerRepository;
import com.barclays.AccountManagementSystem.Repositories.RoleRepository;
import com.barclays.AccountManagementSystem.Repositories.UserRepository;
import com.barclays.AccountManagementSystem.Services.RoleServices;

@ExtendWith(MockitoExtension.class)
public class RoleServicesTest {
	
	@InjectMocks
	private RoleServices roleService;
	
	@Mock
	private RoleRepository roleRepo;
	
	@Mock
	private BankCustomerRepository bankCustomerRepo;
	
	@Mock
	private BankAccountRepository bankAccountRepo;
	
	@Mock
	private UserRepository userRepo;
	
	@Test
	public void createManagerTest() {
		Role role = new Role();
		
		when(roleRepo.save(any(Role.class))).thenReturn(null);
		//doNothing().when(roleRepo).save(role);
		
		Role res = roleService.createManager(role);
		assertEquals(role.getName(), res.getName());
	}
	
	@Test
	public void createUserTest() {
		BankCustomer userDetails = new BankCustomer(12345678, 1234567895, "ashdasjd", "BH@jkas.cbhas", "sbahjasbcbasj", new Date());
		
		when(bankCustomerRepo.findByPanCard(12345678)).thenReturn(null).thenReturn(userDetails);
		when(userRepo.save(any(User.class))).thenReturn(null);
		when(roleRepo.save(any(Role.class))).thenReturn(null);
		when(bankCustomerRepo.save(any(BankCustomer.class))).thenReturn(null);
		when(bankAccountRepo.save(any(BankAccount.class))).thenReturn(null);
		
		BankCustomer bankCust = roleService.createUser(userDetails);
		assertEquals(userDetails.getCustomerID(), bankCust.getCustomerID());
	}
}
