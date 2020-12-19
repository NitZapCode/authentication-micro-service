package com.curatorsodisha.web.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDataRepository extends CrudRepository<UsersEntity, Long> {

	UsersEntity findByUserId(String userId);
}
