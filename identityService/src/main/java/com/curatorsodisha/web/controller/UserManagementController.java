package com.curatorsodisha.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curatorsodisha.web.shared.UserDetailsDTO;
import com.google.common.collect.Lists;

@RestController
@RequestMapping("/management/identity")
public class UserManagementController {
	
	
	@GetMapping(value= {"", "/{userId}"})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMIN_TRAINEE')")
	public ResponseEntity<List<UserDetailsDTO>> getUserDetails(
			@PathVariable(required = false, name = "userId") String userId){
		
		UserDetailsDTO user1 = new UserDetailsDTO();
		user1.setUserId("j1df");
		user1.setFirstName("Jay");
		user1.setLastName("Pandit");
		user1.setEmail("vijayp@gmail.com");
		user1.setContactNumber("9093233212");

		UserDetailsDTO user2 = new UserDetailsDTO();
		user2.setUserId("kfgj");
		user2.setFirstName("Parimal");
		user2.setLastName("Ade");
		user2.setEmail("Parim@gmail.com");
		user2.setContactNumber("9093213212");
		
		List<UserDetailsDTO> userDetailsDTO = null;
		
		if(null!=userId) {
			userDetailsDTO = Lists.newArrayList(user1);
		}else {
			userDetailsDTO = Lists.newArrayList(user1, user2);
		}
		return ResponseEntity.status(HttpStatus.OK).body(userDetailsDTO);
	}
	
	
	@PutMapping(value = "/{userId}")
	@PreAuthorize("hasAuthority('product:write')")
	public ResponseEntity<UserDetailsDTO> updateUserDetails(
			@PathVariable(name = "userId") String userId,
			@RequestBody UserDetailsDTO updatedUserDetails){
		
		UserDetailsDTO user2 = new UserDetailsDTO();
		user2.setUserId("kfgj");
		user2.setFirstName("Paresh");
		user2.setLastName("Ade");
		user2.setEmail("Parim@gmail.com");
		user2.setContactNumber("9093213212");
		
		return ResponseEntity.status(HttpStatus.OK).body(user2);
	}
	
	@DeleteMapping(value = "/{userId}")
	@PreAuthorize("hasAuthority('product:write')")
	public ResponseEntity<UserDetailsDTO> deleteUserDetails(
			@PathVariable(name = "userId") String userId){
		
		UserDetailsDTO user2 = new UserDetailsDTO();
		user2.setUserId("kfgj");
		user2.setFirstName("Bhairav");
		user2.setLastName("Ade");
		user2.setEmail("Parim@gmail.com");
		user2.setContactNumber("9093213212");
		
		return ResponseEntity.status(HttpStatus.OK).body(user2);
	}

}
