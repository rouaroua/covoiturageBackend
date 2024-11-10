package com.covoiturage.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covoiturage.dto.HelloResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class HelloController {

	@GetMapping("/hello")
	public HelloResponse hello() {
		return new HelloResponse("Hello from authorized api request");
	}
	
	
}
