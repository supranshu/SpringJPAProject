package com.apitask.service;

import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationException extends AuthenticationException{

	public CustomAuthenticationException(String msg) {
		super(msg);
		
	}

}
