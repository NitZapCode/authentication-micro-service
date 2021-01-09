package com.curatorsodisha.web.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curatorsodisha.web.shared.CreateUserRequestDTO;
import com.curatorsodisha.web.shared.UserDetailsDTO;
import com.curatorsodisha.web.shared.UserLoginRequestDTO;
import com.curatorsodisha.web.shared.UserLoginResponseDTO;

@RestController
@RequestMapping("/identity")
public class UserRegistrationController {
	
	@PostMapping
	public ResponseEntity<UserDetailsDTO> createUser(
			@RequestBody CreateUserRequestDTO createUserRequest){
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping(value = "/signin")
	public ResponseEntity<UserLoginResponseDTO> userLogin(
			@RequestBody UserLoginRequestDTO userloginDetails){
		UserLoginResponseDTO userlogin = new UserLoginResponseDTO();
		userlogin.setUserName("cuser");
		userlogin.setAccess(Arrays.asList("basic","student"));
		userlogin.setUserId("jkjdjkf");
		
		return ResponseEntity.status(HttpStatus.OK).body(userlogin);
	}
	
	@GetMapping(value= {"/{userId}"})
	public ResponseEntity<UserDetailsDTO> getUserDetails(
			@PathVariable(name = "userId") String userId){
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	

}
