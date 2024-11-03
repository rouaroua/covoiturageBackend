package com.covoiturage.service.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.covoiturage.model.User;
import com.covoiturage.repository.UserRepository;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserDetailsService {

   private final UserRepository userRepository;

   @Autowired
   public UserServiceImpl(UserRepository userRepository) {
       this.userRepository = userRepository;
   }

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user = userRepository.findByEmail(email)
               .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
       return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.emptyList());
   }
}
