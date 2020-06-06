package com.init.login.response;

import org.springframework.http.ResponseEntity;

public class ResponseCrud {

	public  String Message;
	public 	ResponseEntity ResponseEntity;

	
	public void setMessage(String message) {
		Message = message;
	}
	
	public void setResponseEntity(ResponseEntity responseEntity) {
		ResponseEntity = responseEntity;
	}
	
	
}


	
	
	

