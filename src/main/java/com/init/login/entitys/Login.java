package com.init.login.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")

public class Login {
	
	@Id
	@Column(name="Name",nullable=false,length=30)
	private String Name;
	
	@Column(name="Password",nullable=false,length=30)
	private String Password;

	public String getName() {
		return Name;
	}

	 
	public void setName(String name) {
		Name = name;
	}

	 
	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	

}
