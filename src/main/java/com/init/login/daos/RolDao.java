package com.init.login.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.init.login.model.Login;
import com.init.login.model.Rol;

public interface RolDao extends JpaRepository<Rol,int[]> {
	
	
	
	@Query(
			  value = "SELECT namerol FROM roles INNER JOIN user_roles ON roles.idroles = user_roles.idroles where userlogin.user_roles.username=binary ?1", 
			  nativeQuery = true)
  List<String> QueryRoles(String name);

}
