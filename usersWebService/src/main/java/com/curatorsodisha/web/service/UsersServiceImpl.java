package com.curatorsodisha.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curatorsodisha.web.data.UsersDataRepository;
import com.curatorsodisha.web.data.UsersEntity;
import com.curatorsodisha.web.ui.shared.UsersDTO;

@Service
public class UsersServiceImpl implements UsersService {
	
	
	UsersDataRepository userRepository;

	@Autowired
	public UsersServiceImpl(UsersDataRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UsersDTO createUser(UsersDTO user) {
		
		
		ModelMapper mapper  = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UsersEntity userEntity =  mapper.map(user, UsersEntity.class);
		userEntity.setUserId(UUID.randomUUID().toString());
		userEntity = userRepository.save(userEntity);

		UsersDTO responseDTO = mapper.map(userEntity, UsersDTO.class);
		
		return responseDTO;
	}

	@Override
	public List<UsersDTO> getUsers() {
		List<UsersDTO> usersList = new ArrayList<UsersDTO>();
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	    (userRepository.findAll()).forEach((UsersEntity userRecord) -> {
	    	usersList.add(mapper.map(userRecord,UsersDTO.class));
		});
		return usersList;
	}

	@Override
	public List<UsersDTO> getUser(String userId) {

		List<UsersDTO> usersList = new ArrayList<UsersDTO>();
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		//To-DO check for Optional
		UsersEntity userResult =  userRepository.findByUserId(userId);
		if(null != userResult){
			usersList.add(mapper.map(userResult,UsersDTO.class));
		}
		return usersList;
		
	}

	@Override
	public UsersDTO updateUser(UsersDTO user) {
		
		UsersEntity userEntity = userRepository.findByUserId(user.getUserId());
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	    mapper.map(user, userEntity);
		userEntity = userRepository.save(userEntity);
		UsersDTO userdto =  mapper.map(userEntity, UsersDTO.class);
		return userdto;
	}

	@Override
	public String deleteUser(String userId) {
		UsersEntity userToBeDeleted = userRepository.findByUserId(userId);
		userRepository.deleteById(userToBeDeleted.getId());
		return userToBeDeleted.getUserId();
	}

}
