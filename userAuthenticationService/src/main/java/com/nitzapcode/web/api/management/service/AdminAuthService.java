package com.nitzapcode.web.api.management.service;

import java.util.List;

import com.nitzapcode.web.api.dto.UserDTO;

public interface AdminAuthService {

	List<UserDTO> getAllUserDetails();
	
	UserDTO getUser(String userId);
}
