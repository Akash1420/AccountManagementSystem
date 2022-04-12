package com.barclays.AccountManagementSystem.Entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class AccountTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long transactionId;

	private String referenceNumber;

	private Date dateTime;

	private String transactionType; //credit, Debit, wid

	private String subType;

	private double balance;
    
	private long accountNumber;
	
	private long secondPartyAccount;
	

	public AccountTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountTransaction(long transactionId, String referenceNumber, Date dateTime, String transactionType,
			String subType, double balance, long accountNumber, long receiverAccount) {
		super();
		this.transactionId = transactionId;
		this.referenceNumber = referenceNumber;
		this.dateTime = dateTime;
		this.transactionType = transactionType;
		this.subType = subType;
		this.balance = balance;
		this.accountNumber = accountNumber;
		this.secondPartyAccount=receiverAccount;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}


	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getSecondPartyAccount() {
		return secondPartyAccount;
	}

	public void setSecondPartyAccount(long receiverAccount) {
		this.secondPartyAccount = receiverAccount;
	}
	
	
	
}
