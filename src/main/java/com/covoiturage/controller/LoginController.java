package com.covoiturage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covoiturage.dto.LoginRequest;
import com.covoiturage.dto.LoginResponse;
import com.covoiturage.service.jwt.UserServiceImpl;
import com.covoiturage.utils.JwtUtil;



@RestController
@RequestMapping("/login")
public class LoginController {
  
	private final AuthenticationManager authenticationManager;
	private final UserServiceImpl userService;
	private final JwtUtil jwtUtil;
	@Autowired 
	public LoginController(AuthenticationManager authenticationManager, UserServiceImpl userService , JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}
	
	
	@PostMapping
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
					);
		}catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			
		}
		UserDetails userDetails;
		try {
			userDetails = userService.loadUserByUsername(loginRequest.getEmail());
			
		}catch(UsernameNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		}
		String jwt =jwtUtil.generateToken(userDetails.getUsername());
		return ResponseEntity.ok(new LoginResponse(jwt));
		
	}
}
