package com.init.login.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.init.login.model.Login;
import com.init.login.model.Rol;
import com.init.login.model.User_Roles;

public interface User_RolesDao extends JpaRepository<User_Roles,int[]> {

	
	@Query(
			  value = "insert into user_roles (username,idroles)values(?1,?2)", 
			  nativeQuery = true)
  List<Login> insertRol(String username, int rol);
	
}
