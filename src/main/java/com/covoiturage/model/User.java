 package com.covoiturage.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity

public class User implements Serializable {
	  
	 	private static final long serialVersionUID = 1L;
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY) // or AUTO, SEQUENCE, etc.

	    private Long id;
	    private String name;
	    private String email;
	    private String phone;
	    private String userpassword;
	    public User() {};
	    public User(Long id,String name,String email,String phone,String password) {
	    	this.id=id;
	    	this.name=name;
	    	this.email=email;
	    	this.phone=phone;
	    	this.userpassword=password;
	    	
	    }
	    public Long getUserId() {
	        return id;
	    }

	    public void setUserId(Long userId) {
	        this.id = userId;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setUserName(String userName) {
	        this.name = userName;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setUserEmail(String userEmail) {
	        this.email = userEmail;
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public void setUserPhone(String userPhone) {
	        this.phone = userPhone;
	    }

	    public String getPassword() {
	        return userpassword;
	    }

	    public void setUserPassword(String userPassword) {
	        this.userpassword = userPassword;
	    }
}
