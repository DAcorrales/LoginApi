package com.init.login.controller;
import java.time.LocalDateTime;
import java.util.List;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.init.login.daos.EventLogDao;
import com.init.login.daos.LoginDao;
import com.init.login.daos.RolDao;
import com.init.login.daos.User_RolesDao;
import com.init.login.model.EventLog;
import com.init.login.model.Login;
import com.init.login.model.Rol;
import com.init.login.response.GeneralResponse;
import com.init.login.response.RequestChangePassword;
import com.init.login.response.Response;
import com.init.login.response.ResponseCrud;
import com.init.login.token.GetJwtToken;
import com.init.login.token.ValidationJwtToken;
import com.sun.el.stream.Optional;





@RestController
@RequestMapping("/")

public class LoginRestController {
	
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private User_RolesDao userRolesDao;
	
	@Autowired
	private RolDao rolDao;
	GetJwtToken generateToken=new GetJwtToken();
	ValidationJwtToken validationJwtToken=new ValidationJwtToken();
	@Autowired
	private EventLogDao eventLogDao;
	Response response=new Response();
	
	
	//It allows login an user in the system, obtaining a validation Bearer Token	
	@RequestMapping(value="getlogin",method=RequestMethod.GET)
	public Response getUsers(@RequestParam("name") String name, @RequestParam("password") String password)
	{
		EventLog event=new EventLog();
		int remainingAttempts=3;
		List<Login> users ;
		
		try
		{
			users = loginDao.queryLogin(name, password);
			if(users.isEmpty())
			{
				java.util.Optional<Login> userLogin= loginDao.findById(name);
				{
					if(!userLogin.isEmpty())
					{
						Login userTryLogin=userLogin.get();
						if(remainingAttempts>=0)
						{
							remainingAttempts=2-userTryLogin.getLocked();
			
						}
						else
						{
							remainingAttempts=0;
						}
						userTryLogin.setLocked(userTryLogin.getLocked()+1);
						loginDao.save(userTryLogin);
						
					}
					
				}
				response.setResponseLogin("Usuario o Password incorrecto: Intentos restantes= "+remainingAttempts);
			    response.setToken(null);
			}
			    
			else
			{
				if(users.get(0).getLocked()>2)
				{
					response.setToken(null);  
					response.setResponseLogin("Usuario Bloqueado por intentos fallidos");
				}
				else
				{
					users.get(0).setLocked(0);
					String fecha= LocalDateTime.now().toString();		
					event.setDate(fecha);
					event.setName(name);

					eventLogDao.save(event);
					
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
		}
		catch (Exception e)
		{
			response.setResponseLogin("Operación fallida: "+e);
		}
		
		
		return response;
	}
	
	
	//It allows login an user in the system, obtaining a validation Bearer Token	
	@RequestMapping(value="changepassword",method=RequestMethod.PUT)
	public GeneralResponse changepassword(@RequestBody RequestChangePassword request)
	{
		
		GeneralResponse generalResponse=new GeneralResponse();
		List<Login> users ;
		
		try
		{
			users = loginDao.queryLogin(request.getName().toString(), request.getCurrentpassword().toString());
			if(users.isEmpty())
			{
				generalResponse.setMessage("Usuario o password incorrecto");
				
			}
			    
			else
			{
				if(request.getNewpassword().equals(request.getRenewpassword()))
				{
					Login changingUser=users.get(0);
					changingUser.setPassword(request.getNewpassword());
					try {
						
						loginDao.save(changingUser);
						generalResponse.setMessage("Cambio de Password exitoso");
						
					}
					catch(Exception e)
					{
						generalResponse.setMessage("Operación Falló: "+e);
					}
					
				}
				else
				{
					generalResponse.setMessage("Operación Falló: El password reescrito no coincide");
				}
			
			
			}
		}
		catch (Exception e)
		{
			response.setResponseLogin("Operación fallida: "+e);
		}
		
		
		return generalResponse;
	}
	
	
	
	
	
	
	
	
	   //It allows to save an user in database of the system	
		@RequestMapping(value="adduser",method=RequestMethod.POST)
		public ResponseCrud adduser(@RequestHeader(name = "Token") String loginToken,@RequestBody Login user)
		{
			ResponseCrud responseCrud=new ResponseCrud();
			Rol rol=new Rol();
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