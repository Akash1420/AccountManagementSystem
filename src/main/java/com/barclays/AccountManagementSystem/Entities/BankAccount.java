package com.barclays.AccountManagementSystem.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class BankAccount {

	@Id
    private long accountNumber;
    private long customerId;
    private double balance;
	
    
    
    public BankAccount(long accountNumber, long customerId, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.customerId = customerId;
		this.balance = balance;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
    
    
}
