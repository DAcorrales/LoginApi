package com.init.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_roles")

public class Rol {
	
	@Id
	@Column(name="user_role_id",nullable=false,length=11)
	private int User_role_id;
	
	@Column(name="username",nullable=false,length=30)
	private String Username;
	
	@Column(name="role",nullable=false,length=45)
	private String Role;

	public int getUser_role_id() {
		return User_role_id;
	}

	public String getUsername() {
		return Username;
	}

	public void setUser_role_id(int user_role_id) {
		User_role_id = user_role_id;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public void setRole(String role) {
		Role = role;
	}

	public String getRole() {
		return Role;
	}

	
	

}
