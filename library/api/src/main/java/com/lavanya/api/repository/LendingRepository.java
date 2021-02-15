package com.lavanya.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lavanya.api.model.Lending;

/**
 * Repository extending JPA repository for persistence of Lending object.
 * @author lavanya
 */
@Repository
public interface LendingRepository extends JpaRepository<Lending, Integer> {
	
	/**
	 * Query to retrieve the list of lending of a specific user ordered by due date.
	 * @param userId for id of the user of interest.
	 * @return List of lending.
	 */
	public List<Lending>findAllByUserIdOrderByDueDate(int userId);
	
	/**
	 * Query to retrieve a lending of interest.
	 * @param id for id of the lending of interest.
	 * @return Optional lending.
	 */
	public Optional<Lending> findById(Integer id);
	
}
