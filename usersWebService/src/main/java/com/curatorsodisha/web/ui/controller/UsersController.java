package com.curatorsodisha.web.ui.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curatorsodisha.web.service.UsersService;
import com.curatorsodisha.web.ui.shared.UsersDTO;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	private UsersService usersService;
	
	@Autowired
	public UsersController(UsersService userService) {
		this.usersService = userService;
	}
	
	@GetMapping(
			     value = {"","/{userId}"}, 
			     produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
	public ResponseEntity<List<UsersDTO>> getUsers(@PathVariable(required = false, name = "userId") String userId){
		List<UsersDTO> responseUsersList = null;
		if(null == userId){
			responseUsersList = usersService.getUsers();
		}else {
			responseUsersList = usersService.getUser(userId);
		}
		return ResponseEntity.ok(responseUsersList);
	}
	
	@PostMapping(
			      consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			      produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
	public ResponseEntity<UsersDTO> createUser(@Valid @RequestBody UsersDTO userDetails){
		UsersDTO userDto = usersService.createUser(userDetails);
		return ResponseEntity.ok(userDto);
	}
	
	@PutMapping
	public ResponseEntity<UsersDTO> updateUsers(@RequestBody UsersDTO userDetails){
		UsersDTO responseDto = usersService.updateUser(userDetails);
		return ResponseEntity.ok(responseDto);
	}
	
	@DeleteMapping(value="/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable String userId){
		
		usersService.deleteUser(userId);
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}

}
