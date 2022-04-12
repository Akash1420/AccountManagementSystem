package com.barclays.AccountManagementSystem.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.AccountManagementSystem.Entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByCustomerIDAndPassword(long customerId, String password );
	User findByCustomerIDAndTempPassword(long customerId, String tempPassword );
	User findByCustomerID(long customerId );
	
}
