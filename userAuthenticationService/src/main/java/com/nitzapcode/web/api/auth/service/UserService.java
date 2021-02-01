package com.nitzapcode.web.api.auth.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nitzapcode.web.api.dto.UserDTO;

public interface UserService extends UserDetailsService {
   
	UserDTO registerUser(UserDTO userRegObj);
	
}
