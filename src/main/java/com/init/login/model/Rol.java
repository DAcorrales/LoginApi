package com.init.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user_roles")

public class Rol {
	


	@Id
	@Column(name="user_role_id",nullable=false,length=11)
	private int user_role_id;
	
	@Column(name="idroles",nullable=false,length=11)
	private int idroles;
	
	@Column(name="username",nullable=false,length=30)
	private String Username;
	

	public int getUser_role_id() {
		return user_role_id;
	}

	public void setUser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}

	public int getIdroles() {
		return idroles;
	}

	public void setIdroles(int idroles) {
		this.idroles = idroles;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}
	
	


}
