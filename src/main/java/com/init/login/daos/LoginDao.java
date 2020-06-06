package com.init.login.daos;

import java.util.List;

import javax.persistence.Tuple;
import com.init.login.response.ResponseQueryUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.init.login.model.Login;
import com.init.login.response.ResponseQueryUser;

public interface LoginDao extends JpaRepository<Login,String> {

	@Query(
			  value = "SELECT * FROM users u WHERE u.name = binary ?1 AND u.password= binary?2", 
			  nativeQuery = true)
    List<Login> queryLogin(String name, String password);
	
	
	@Query(
			  value = "select users.name,users.fullname,users.companyname,users.active,\n" + 
			  		"users.locked,\n" + 
			  		"users.emailaddress\n" + 
			  		",roles.namerol from\n" + 
			  		"((users inner join user_roles on users.name= user_roles.username)\n" + 
			  		"inner join roles on user_roles.idroles=roles.idroles) order by name", 
			  nativeQuery = true)
	List<Object> queryuser();
	
	
	
}
