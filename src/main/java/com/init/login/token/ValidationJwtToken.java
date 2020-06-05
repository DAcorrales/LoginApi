package com.init.login.token;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.init.login.controller.JWTAuthorizationFilter;

public class ValidationJwtToken {
	
	public Boolean isToken(String token)
	{
		
		JWTAuthorizationFilter authFilter=new JWTAuthorizationFilter();
		
		List<GrantedAuthority> isToken=authFilter.validateTokenRol(token);
		   if(isToken!=null)
		   {
			  return true;   
		   }
		   else
		   {
			   return false;
		   }
		
	}
	
	public Boolean isAuthorizedRol(List<GrantedAuthority> Rol,String Rol_Authorized)
	{
		
		 if(Rol.contains(Rol_Authorized))
			   
			{
		    	return true;
			}
			else
			{
				return false;
			}
	}
	
	

}
