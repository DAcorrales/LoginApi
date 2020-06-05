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
import com.init.login.response.ResponseCrud;
import com.init.login.token.GetJwtToken;
import com.init.login.token.ValidationJwtToken;





@RestController
@RequestMapping("/")

public class LoginRestController {
	
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private RolDao rolDao;
	GetJwtToken generateToken=new GetJwtToken();
	ValidationJwtToken validationJwtToken=new ValidationJwtToken();
	Response response=new Response();
	
	
	//It allows login an user in the system, obtaining a validation Bearer Token	
	@RequestMapping(value="getlogin",method=RequestMethod.GET)
	public Response getUsers(@RequestParam("name") String name, @RequestParam("password") String password)
	{
		try
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
		}
		catch (Exception e)
		{
			response.setResponseLogin("Operación fallida: "+e);
		}
		
		
		return response;
	}
	
	
	   //It allows to save an user in database of the system	
		@RequestMapping(value="adduser",method=RequestMethod.POST)
		public ResponseCrud adduser(@RequestHeader(name = "Token") String loginToken,@RequestBody Login user)
		{
			ResponseCrud responseCrud=new ResponseCrud();
			JWTAuthorizationFilter authFilter=new JWTAuthorizationFilter();
			List<GrantedAuthority> isToken=authFilter.validateTokenRol(loginToken);
			
			 if(validationJwtToken.isToken(loginToken))
			 {
				if( validationJwtToken.isAuthorizedRol(isToken, "MANAGE_USER_ROLES"))
				{
					Login newUser;
					try {
						 newUser = loginDao.save(user);
						 responseCrud.ResponseEntity= ResponseEntity.ok(newUser);
						 responseCrud.Message="Operación realizada exitosamente";
					}
					catch(Exception e)
					{
						responseCrud.Message="Operación fallida: "+e;
					}
					return responseCrud;
					
				}
				else
				{
					responseCrud.Message="No cuenta con los permisos requeridos para acceder a este servicio";
				}
				 
			 }
			 else
			 {
				 responseCrud.Message="Invalid Token";
			 }
			 
			 return responseCrud;

		}
	
		
	
	
	

	
	@RequestMapping(value="getuserinformation",method=RequestMethod.GET)
	public String hello(@RequestHeader(name = "Token") String loginToken)
	{
		JWTAuthorizationFilter authFilter=new JWTAuthorizationFilter();
		
		 if(validationJwtToken.isToken(loginToken))
		 {
			 
		 }
		 else
		 {
			 
		 }
		
		
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
