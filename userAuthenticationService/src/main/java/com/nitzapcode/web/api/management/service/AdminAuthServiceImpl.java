package com.nitzapcode.web.api.management.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitzapcode.web.api.auth.data.UserEntity;
import com.nitzapcode.web.api.auth.data.UserRepository;
import com.nitzapcode.web.api.dto.UserDTO;

@Service
public class AdminAuthServiceImpl implements AdminAuthService{

	private UserRepository userRepository;
	
	@Autowired
	public AdminAuthServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public List<UserDTO> getAllUserDetails() {
		
		ModelMapper mapper =  new ModelMapper();
		List<UserEntity> userEntityList = (List<UserEntity>)userRepository.findAll();
		List<UserDTO> userDtoList =  userEntityList.stream()
				.map(entity -> mapper.map(entity, UserDTO.class))
				.collect(Collectors.toList());
		
		return userDtoList;
	}

	@Override
	public UserDTO getUser(String userId) {

		ModelMapper mapper =  new ModelMapper();
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(null == userEntity) {
			throw new RuntimeException();
		}
		UserDTO userDto =  mapper.map(userEntity, UserDTO.class);
		return userDto;
		
	}

	
	
	
}
