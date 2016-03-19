package com.medisys.repositories;

import java.time.LocalDateTime;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medisys.entities.LoginCredentials;
import com.medisys.entities.User;

@Repository("LoginCredentialsRepository")
public interface LoginCredentialsRepository extends
		PagingAndSortingRepository<LoginCredentials, Long> {
	LoginCredentials findByUser(@Param("user") User user);

	LoginCredentials findActiveSession(@Param("sessionKey") String sessionKey,
			@Param("expirationDate") LocalDateTime expirationDate);
}
