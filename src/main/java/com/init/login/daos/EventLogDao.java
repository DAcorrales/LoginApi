package com.init.login.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.init.login.model.EventLog;
import com.init.login.model.Login;
import com.init.login.model.Rol;

public interface EventLogDao extends JpaRepository<EventLog,int[]> {
	
	
	@Query(
			  value = "insert into eventlog (idevent, name,date) values(1,'s','y')", 
			  nativeQuery = true)
  public void SaveEvent(String name,String Date);
	
	
	

}



