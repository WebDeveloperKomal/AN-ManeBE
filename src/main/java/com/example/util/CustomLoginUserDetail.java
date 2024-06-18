package com.example.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.LoginUserDetails;
import com.example.repo.LoginUserDetailsRepository;

@Service
public class CustomLoginUserDetail implements UserDetailsService {
	

	@Autowired
	private LoginUserDetailsRepository loginUserDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        LoginUserDetails loginUserDetails = loginUserDetailsRepository.findByEmail(username)
	        		  .orElseThrow(() -> new RuntimeException("User not found !!"));
		return loginUserDetails;
	}

}
