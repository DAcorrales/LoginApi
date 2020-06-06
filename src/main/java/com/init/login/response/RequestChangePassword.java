package com.init.login.response;

import org.springframework.beans.factory.annotation.Required;

public class RequestChangePassword {
	
	public String name;
	public String currentpassword;
	public String newpassword;
	public String renewpassword;
	

	public String getName() {
		return name;
	}
	
	 @Required
	public void setName(String name) {
		this.name = name;
	}
	public String getCurrentpassword() {
		return currentpassword;
	}
	
	@Required
	public void setCurrentpassword(String currentpassword) {
		this.currentpassword = currentpassword;
	}
	public String getNewpassword() {
		return newpassword;
	}
	
	@Required
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getRenewpassword() {
		return renewpassword;
	}
	
	@Required
	public void setRenewpassword(String renewpassword) {
		this.renewpassword = renewpassword;
	}
	
	

	
}
