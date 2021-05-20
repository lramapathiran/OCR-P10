package com.lavanya.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lavanya.api.model.Lending;

/**
 * Repository extending JPA repository for persistence of Lending object.
 * @author lavanya
 */
@Repository
public interface BatchRepository extends JpaRepository<Lending, Integer>{
	
	/**
	 * Query to retrieve the list of lending of overdue books .
	 * @param todayDate date of today used to compared to lending due date.
	 * @return List of lending not returned.
	 */
	@Query("select u from Lending u where ?1 > u.dueDate")
	List<Lending> getLendingsNotInTime(LocalDate todayDate);

}
