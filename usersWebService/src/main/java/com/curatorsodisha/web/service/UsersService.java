package com.curatorsodisha.web.service;

import java.util.List;

import com.curatorsodisha.web.ui.shared.UsersDTO;

public interface UsersService {
	
	UsersDTO createUser(UsersDTO user);
	List<UsersDTO> getUsers();
	List<UsersDTO> getUser(String userId);
	UsersDTO updateUser(UsersDTO user);
	String deleteUser(String userId);
}
