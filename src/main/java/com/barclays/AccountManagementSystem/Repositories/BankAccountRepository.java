package com.barclays.AccountManagementSystem.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.AccountManagementSystem.Entities.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
	public List<BankAccount> findByCustomerId(long customerId);
}
