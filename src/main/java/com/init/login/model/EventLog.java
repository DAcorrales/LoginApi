package com.init.login.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="eventlog")

public class EventLog {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idevent;
	
	@Column(name="name",nullable=false,length=45)
	private String name;
	
	@Column(name="date",nullable=false,length=70)
	private String date;

	public int getIdevent() {
		return idevent;
	}

	public void setIdevent(int idevent) {
		this.idevent = idevent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	


}
