package com.init.login.model;

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
	
	@Column(name="fullname",nullable=false,length=45)
	private String FullName;
	
	@Column(name="companyname",nullable=true,length=45)
	private String CompanyName;
	
	@Column(name="emailaddress",nullable=true,length=60)
	private String EmailAddress;
	
	@Column(name="position",nullable=true,length=45)
	private String Position;
	
	@Column(name="phone",nullable=true,length=45)
	private String Phone;
	
	@Column(name="workingaddress",nullable=true,length=45)
	private String WorkingAddress;
	
	
	public String getFullName() {
		return FullName;
	}


	public void setFullName(String fullName) {
		FullName = fullName;
	}


	public String getCompanyName() {
		return CompanyName;
	}


	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}


	public String getEmailAddress() {
		return EmailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}


	public String getPosition() {
		return Position;
	}


	public void setPosition(String position) {
		Position = position;
	}


	public String getPhone() {
		return Phone;
	}


	public void setPhone(String phone) {
		Phone = phone;
	}


	public String getWorkingAddress() {
		return WorkingAddress;
	}


	public void setWorkingAddress(String workingAddress) {
		WorkingAddress = workingAddress;
	}


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
