package com.barclays.AccountManagementSystem.Entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BankCustomer {
	@Id
	private long customerID;
	private long panCard;
	private long aadhar;
	private String name;
	private String email;
	private String address;
	private	Date dob;
	
	public BankCustomer( long panCard, long aadhar, String name, String email, String address,
			Date dob) {
		super();
		
		this.panCard = panCard;
		this.aadhar = aadhar;
		this.name = name;
		this.email = email;
		this.address = address;
		this.dob = dob;
	}
	
	public BankCustomer() {
		super();
	}

	public long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}
	public long getPanCard() {
		return panCard;
	}
	public void setPanCard(long panCard) {
		this.panCard = panCard;
	}
	public long getAadhar() {
		return aadhar;
	}
	public void setAadhar(long aadhar) {
		this.aadhar = aadhar;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	
}
