package com.lavanya.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lavanya.api.model.User;

/**
 * Repository extending JPA repository for persistence of User object.
 * @author lavanya
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	/**
	 * Query to retrieve a specific user using his username.
	 * @param username used to identify the user searched in database.
	 * @return the User of interest.
	 */
	User findByUsername(String username);
	
	/**
	 * Query to verify if a user with a specific username exists in database.
	 * @param username used to identify the user searched in database.
	 * @return boolean false or true if user exists or not.
	 */
	Boolean existsByUsername(String username);

	/**
	 * Query to retrieve a specific user using his email address.
	 * @param email used to identify the user searched in database.
	 * @return the User of interest.
	 */
	Boolean existsByEmail(String email);

}
