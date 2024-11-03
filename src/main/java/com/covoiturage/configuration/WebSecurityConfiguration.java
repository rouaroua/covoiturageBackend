package com.covoiturage.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.covoiturage.filters.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration {
	
	private final JwtRequestFilter jwtRequestFilter;
	@Autowired
	public WebSecurityConfiguration(JwtRequestFilter jwtRequestFilter) {
		this.jwtRequestFilter = jwtRequestFilter;
	}
	
	  @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	            .authorizeHttpRequests()
	                .requestMatchers("/signup","/login").permitAll() // Allow public access to signup
	                .and()
	                .authorizeHttpRequests().requestMatchers("/api/**")
	                .authenticated() // Require authentication for all other requests
	                .and()
	            .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless session
	                .and()
	        .addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
	        return http.build();
	    }
	

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    	return configuration.getAuthenticationManager();
    	
    }
}
