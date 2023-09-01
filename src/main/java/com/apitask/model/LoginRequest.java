package com.apitask.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoginRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String loginId;
	private String password;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin_Id() {
		return loginId;
	}
	public void setLogin_Id(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginRequest(Long id, String loginId, String password) {
		super();
		this.id = id;
		this.loginId = loginId;
		this.password = password;
	}
	public LoginRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
