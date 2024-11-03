package com.covoiturage.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.covoiturage.dto.SignupRequest;
import com.covoiturage.model.User;
import com.covoiturage.repository.UserRepository;
@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	@Autowired
	public AuthServiceImpl(UserRepository userRepository , PasswordEncoder passwordEncoder ) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public boolean createUser(SignupRequest signupRequest) {
		//check if he already exists
		if(userRepository.existsByEmail(signupRequest.getEmail())) {
			return false;
		}
		User user = new User();
		
		user.setUserEmail(signupRequest.getEmail());
		user.setUserName(signupRequest.getName());
		user.setUserPhone(signupRequest.getPhone());
		String hashPassowrd = passwordEncoder.encode(signupRequest.getPassword());
		user.setUserPassword(hashPassowrd);
		userRepository.save(user);

		return true;
	}
}
