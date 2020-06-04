package com.init.login.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.init.login.daos.LoginDao;
import com.init.login.daos.RolDao;
import com.init.login.model.Login;
import com.init.login.model.Rol;
import com.init.login.response.Response;
import com.init.login.token.GetJwtToken;





@RestController
@RequestMapping("/")

public class LoginRestController {
	
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private RolDao rolDao;
	GetJwtToken generateToken=new GetJwtToken();
	Response response=new Response();
		
	@RequestMapping(value="getlogin",method=RequestMethod.GET)
	public Response getUsers(@RequestParam("name") String name, @RequestParam("password") String password)
	{
		List<Login> users = loginDao.queryLogin(name, password);
		
		
		
		if(users.isEmpty())
		{
			response.setResponseLogin("Usuario o Password incorrecto");
		    response.setToken(null);
		}
		    
		else
		{
			String stringRoles=null;
			List<String> rolesUser= rolDao.QueryRoles(name);
			for(String rol:rolesUser)
			{
				if(stringRoles==null)
				{
					stringRoles=rol;
				}
				else
				{
					stringRoles=stringRoles+","+rol;
				}
				
			}
			String sendRoles=stringRoles==null?"ROLE_USER":stringRoles;
			response.setToken(generateToken.getJWTToken(name,sendRoles));  
			response.setResponseLogin("Login exitoso");
		}
		
		return response;
	}
	
	
	
	

	
	@RequestMapping(value="getuserinformation",method=RequestMethod.GET)
	public String hello(@RequestHeader(name = "Token") String loginToken)
	{
		JWTAuthorizationFilter authFilter=new JWTAuthorizationFilter();
		
		
		List<GrantedAuthority> isToken=authFilter.validateTokenRol(loginToken);
		   if(isToken!=null)
		   {
			   if(isToken.contains("ADMIN_USER"))
				   
				{
			    	return "Correct rol in token  "+loginToken;
				}
				else
				{
					return "No tiene los permisos requeridos para utilizar esta función";
				}
			   
		   }
		   else
		   {
			   return "Invalid Token";
		   }
		
		   

		 
	}
	
	
	@RequestMapping(value="response",method=RequestMethod.GET)
	public ResponseEntity<Login> GetLogin(@RequestParam(value = "user") String user, @RequestBody Login name)
	{
		Login login=new Login();
		login.setName(name.getPassword());
		login.setPassword(name.getName());
		
		return ResponseEntity.ok(login);
	}

}
