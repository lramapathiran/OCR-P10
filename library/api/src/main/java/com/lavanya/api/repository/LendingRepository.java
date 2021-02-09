package com.lavanya.api.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lavanya.api.model.Lending;

@Repository
public interface LendingRepository extends JpaRepository<Lending, Integer> {
	
	public List<Lending>findAllByUserId(Integer id);
	
	public Optional<Lending> findById(Integer id);
	
	public Lending findFirstByOrderByIdDesc();
	
	@Query("select u from Lending u where u.user=?1 and u.lendingDate=?2")
	public Lending findLastRecordByUserId(Integer userId, LocalDate date);

}
