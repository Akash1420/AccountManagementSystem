package com.barclays.AccountManagementSystem.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.AccountManagementSystem.Entities.AccountTransaction;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long>{
	List<AccountTransaction> findByAccountNumber(long accountNumber);
}
