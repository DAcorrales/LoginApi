package com.init.login.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.init.login.model.Login;

public interface LoginDao extends JpaRepository<Login,String> {

	@Query(
			  value = "SELECT * FROM users u WHERE u.name = binary ?1 AND u.password= binary?2", 
			  nativeQuery = true)
    List<Login> queryLogin(String name, String password);
	
	

}
