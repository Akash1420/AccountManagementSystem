package com.barclays.AccountManagementSystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.AccountManagementSystem.Entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
