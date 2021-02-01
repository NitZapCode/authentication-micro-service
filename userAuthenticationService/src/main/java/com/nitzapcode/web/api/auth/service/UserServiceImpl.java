package com.nitzapcode.web.api.auth.service;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nitzapcode.web.api.auth.data.UserEntity;
import com.nitzapcode.web.api.auth.data.UserRepository;
import com.nitzapcode.web.api.dto.UserDTO;
import com.nitzapcode.web.api.security.ApplicationRoles;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user = userRepository.findByUserName(username);
		if(null == user) throw new UsernameNotFoundException(String.format("the given username %s does not exist",username));
		
		return new User(user.getUserName(), user.getEncryptedPassword(), true, true, true, true, ApplicationRoles.valueOf(user.getRole()).getGrantedAUthorities());
	}

	@Override
	public UserDTO registerUser(UserDTO userRegObj) {
		
		ModelMapper mapper = new ModelMapper();
		UserEntity userEntity = mapper.map(userRegObj, UserEntity.class);
		userEntity.setEncryptedPassword(passwordEncoder.encode(userRegObj.getPassword()));
		userEntity.setUserId(UUID.randomUUID().toString());
		userEntity = userRepository.save(userEntity);
		UserDTO responseUserDto = mapper.map(userEntity, UserDTO.class);
		return responseUserDto;
	}
	
}
