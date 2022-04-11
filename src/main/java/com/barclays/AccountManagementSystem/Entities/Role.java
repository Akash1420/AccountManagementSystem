package com.barclays.AccountManagementSystem.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {
	
	@Id
	private long roleID;
	private String name;
	
	
	public Role(long roleID, String name) {
		super();
		this.roleID = roleID;
		this.name = name;
	}
	
	public long getRoleID() {
		return roleID;
	}
	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
