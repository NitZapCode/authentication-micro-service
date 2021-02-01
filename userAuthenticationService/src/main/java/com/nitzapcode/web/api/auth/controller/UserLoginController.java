package com.nitzapcode.web.api.auth.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitzapcode.web.api.auth.service.UserService;
import com.nitzapcode.web.api.dto.UserDTO;
import com.nitzapcode.web.api.dto.UserRegisterRequestDTO;
import com.nitzapcode.web.api.dto.UserRegisterResponseDTO;

@RestController
@RequestMapping("/api/auth")
public class UserLoginController {
	
	private UserService userService;
	
	@Autowired
	public UserLoginController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/user")
	public ResponseEntity<UserRegisterResponseDTO> registerUser(
			@RequestBody UserRegisterRequestDTO userRegistrationObj){
		
		ModelMapper mapper = new ModelMapper();
		UserDTO userRegObj = mapper.map(userRegistrationObj, UserDTO.class);
		UserRegisterResponseDTO responseObj = mapper.map(userService.registerUser(userRegObj), UserRegisterResponseDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseObj);
	}
	
	
}
