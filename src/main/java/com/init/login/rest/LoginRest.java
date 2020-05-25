package com.init.login.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.init.login.daos.LoginDao;
import com.init.login.entitys.Login;
import com.init.login.token.getJwtToken;




@RestController
@RequestMapping("/")

public class LoginRest {
	
	@Autowired
	private LoginDao loginDao;
	GetJwtToken generateToken=new GetJwtToken();
		
	@RequestMapping(value="getlogin",method=RequestMethod.GET)
	public String getUsers(@RequestParam("name") String name, @RequestParam("password") String password)
	{
		List<Login> users = loginDao.queryLogin(name, password);
		if(users.isEmpty())
		{
			return "Usuario o Password incorrecto";
		}
		else
		{
			String token = generateToken.getJWTToken(name);  
			
			return "Login exitoso:"+token;
		}
		
	}
	
	
	

	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String hello(String nombre, String apellido)
	{
		return "Hello world: "+nombre+" "+apellido;
	}
	
	
	@RequestMapping(value="response",method=RequestMethod.GET)
	public ResponseEntity<Login> GetLogin()
	{
		Login login=new Login();
		login.setName("Mariana");
		login.setPassword("Pepito01");
		
		return ResponseEntity.ok(login);
	}

}
