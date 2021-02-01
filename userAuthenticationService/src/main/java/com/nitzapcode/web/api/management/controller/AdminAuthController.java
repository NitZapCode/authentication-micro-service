package com.nitzapcode.web.api.management.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitzapcode.web.api.dto.UserDTO;
import com.nitzapcode.web.api.dto.UserRegisterResponseDTO;
import com.nitzapcode.web.api.management.service.AdminAuthService;

@RestController
@RequestMapping("api/management/user")
public class AdminAuthController {
  
	private AdminAuthService adminAuthService;
	
	@Autowired
	public AdminAuthController(AdminAuthService adminAuthService) {
		this.adminAuthService = adminAuthService;
	}
	
	
	@GetMapping(value = {"","/{userId}"})
	public ResponseEntity<List<UserRegisterResponseDTO>> getUserDetails(
			@PathVariable(required = false, name = "userId") String userId){
		
		List<UserRegisterResponseDTO> userList = null;
		ModelMapper mapper = new ModelMapper();
		
		if(null == userId){
		  List<UserDTO> userDtoList = adminAuthService.getAllUserDetails();
		  userList = userDtoList.stream().map(user -> mapper.map(user,UserRegisterResponseDTO.class)).collect(Collectors.toList());
		  
		}else {
			UserDTO userDto = adminAuthService.getUser(userId);
			userList = new ArrayList<UserRegisterResponseDTO>();
			UserRegisterResponseDTO respDto = mapper.map(userDto, UserRegisterResponseDTO.class);
			userList.add(respDto);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(userList);
	}
	
}
