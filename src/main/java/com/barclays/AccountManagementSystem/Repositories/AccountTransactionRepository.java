package com.barclays.AccountManagementSystem.Repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.AccountManagementSystem.Entities.AccountTransaction;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long>{
	List<AccountTransaction> findByAccountNumber(long accountNumber);
	List<AccountTransaction> findBydateTimeAndAccountNumber(Date dateTime, long accountNumber);
	List<AccountTransaction> findBydateTimeAndSecondPartyAccount(Date dateTime, long secondPartyAccount);
	
}
