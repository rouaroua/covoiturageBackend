package com.covoiturage.service;

import com.covoiturage.dto.SignupRequest;
import com.covoiturage.model.User;

public interface AuthService {
User createUser(SignupRequest signupRequest);
}
