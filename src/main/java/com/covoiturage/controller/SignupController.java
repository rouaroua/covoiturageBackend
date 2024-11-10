package com.covoiturage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covoiturage.dto.SignupRequest;
import com.covoiturage.model.User;
import com.covoiturage.service.AuthService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/signup")
public class SignupController {
	
	
   private final AuthService authService;
   @Autowired
   public SignupController(AuthService authService) {
	   this.authService = authService;
   }
   
   @PostMapping
   public ResponseEntity<User> signupUser(@RequestBody SignupRequest signupRequest){
	   System.out.println("fdsdf");
	   User userCreated = authService.createUser(signupRequest);
	   if(userCreated!=null) {
		   return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
	   }else {
		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	   }
	   
   }

}
