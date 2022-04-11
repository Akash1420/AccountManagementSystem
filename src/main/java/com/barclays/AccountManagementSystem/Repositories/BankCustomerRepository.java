package com.barclays.AccountManagementSystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.AccountManagementSystem.Entities.BankAccount;
import com.barclays.AccountManagementSystem.Entities.BankCustomer;

public interface BankCustomerRepository extends JpaRepository<BankCustomer, Long>{

}
