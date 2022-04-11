package com.barclays.AccountManagementSystem.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	private long userID;
	private long customerID;
	private String password;
	private String tempPassword;
	private long roleID;
	private boolean isTempPassValid;
	
	
//	public User(long userID, long customerID, String password, String tempPassword, long roleID,
//			boolean isTempPassValid) {
//		super();
//		this.userID = userID;
//		this.customerID = customerID;
//		this.password = password;
//		this.tempPassword = tempPassword;
//		this.roleID = roleID;
//		this.isTempPassValid = isTempPassValid;
//	}
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTempPassword() {
		return tempPassword;
	}
	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}
	public long getRoleID() {
		return roleID;
	}
	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}
	public boolean isTempPassValid() {
		return isTempPassValid;
	}
	public void setTempPassValid(boolean isTempPassValid) {
		this.isTempPassValid = isTempPassValid;
	}
	
	
}
