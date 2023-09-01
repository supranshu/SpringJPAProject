package com.apitask.service;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apitask.model.LoginRequest;

@Service
public class AuthenticationService { 
	@Autowired
	private RestTemplate restTemplate;
	
	public String authenticateUser(LoginRequest loginRequest) {
		String apiUrl="https://qa2.sunbasedata.com/sunbase/portal/api";
		
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<LoginRequest> requestEntity=new HttpEntity<>(loginRequest,headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
            // Authentication successful, obtain the bearer token from the response
            String responseBody = responseEntity.getBody();
            // Parse the response JSON to extract the bearer token
            // For example, if the response is in JSON format:
            // String bearerToken = extractBearerTokenFromJson(responseBody);
            return "token12345"; 
        } else {
            // Authentication failed, handle the error appropriately
            throw new CustomAuthenticationException("Authentication failed");
        }
		
	}
	
}
