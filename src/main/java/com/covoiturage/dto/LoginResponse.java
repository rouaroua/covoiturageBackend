package com.covoiturage.dto;

public class LoginResponse {
 private String jwtToken;
 public LoginResponse(String jwtToken) {
	 this.jwtToken=jwtToken;  
 }
 public String getJwt() {
     return jwtToken;
 }
}
