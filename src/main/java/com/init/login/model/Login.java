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
	private String fullname;
	
	@Column(name="companyname",nullable=true,length=45)
	private String companyname;
	
	@Column(name="position",nullable=true,length=60)
	private String position;
	
	@Column(name="phone",nullable=true,length=60)
	private String phone;
	
	@Column(name="emailaddress",nullable=true,length=60)
	private String emailaddress;
	
	@Column(name="workingaddress",nullable=true,length=60)
	private String workingaddress;
	
	@Column(name="active",nullable=true,length=1)
	private int active;

	@Column(name="locked",nullable=true,length=1)
	private int locked;
	

	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWorkingaddress() {
		return workingaddress;
	}

	public void setWorkingaddress(String workingaddress) {
		this.workingaddress = workingaddress;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	

	
	
	
}
