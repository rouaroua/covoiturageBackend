package com.covoiturage.service;

import com.covoiturage.dto.SignupRequest;

public interface AuthService {
boolean createUser(SignupRequest signupRequest);
}
