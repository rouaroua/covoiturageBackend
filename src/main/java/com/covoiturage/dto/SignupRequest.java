package com.covoiturage.dto;

public class SignupRequest {
    private String name;
    private String email;
    private String phone;
    private String password;

    public String getName() {
        return name;
    }

    // Change this method name from setUserName to setName
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    // Change this method name from setUserEmail to setEmail
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    // Change this method name from setUserPhone to setPhone
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    // Change this method name from setUserPassword to setPassword
    public void setPassword(String password) {
        this.password = password;
    }
}
