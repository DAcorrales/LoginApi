package com.init.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_roles")
public class User_Roles {
	
	@Id
	@Column(name="user_role_id",nullable=false,length=11)
	private int user_role_id;
	
	@Column(name="username",nullable=false,length=45)
	private String username;
	
	@Column(name="idroles",nullable=false,length=11)
	private int idroles;

	public int getuser_role_id() {
		return user_role_id;
	}

	public void setuser_role_id(int name) {
		user_role_id = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getIdroles() {
		return idroles;
	}

	public void setIdroles(int idroles) {
		this.idroles = idroles;
	}
	


}
