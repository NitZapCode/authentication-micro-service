package com.nitzapcode.web.api.auth.data;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String>{

	UserEntity findByUserId(String userId);
	UserEntity findByUserName(String userName);
}
