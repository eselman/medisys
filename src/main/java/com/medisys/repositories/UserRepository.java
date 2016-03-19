package com.medisys.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.medisys.entities.User;
import com.medisys.entities.UserStatus;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	User findByUsernameAndStatus(@Param("username") String username, @Param("status") UserStatus status);
}