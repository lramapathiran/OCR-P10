package com.lavanya.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lavanya.api.model.Book;
import com.lavanya.api.model.Lending;

@Repository
public interface BatchRepository extends JpaRepository<Lending, Integer>{
	
	
	@Query("select u from Lending u where ?1 > u.dueDate")
	List<Lending> getLendingsNotInTime(LocalDate todayDate);

}
