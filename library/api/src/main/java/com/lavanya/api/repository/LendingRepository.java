package com.lavanya.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lavanya.api.model.Lending;

@Repository
public interface LendingRepository extends JpaRepository<Lending, Integer> {
	
	public List<Lending>findAllByUserId(Integer id);
	
	public Lending findByBookId(Integer id);

}
