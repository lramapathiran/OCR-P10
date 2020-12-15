package com.lavanya.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lavanya.web.model.Lending;

public interface LendingRepository extends JpaRepository<Lending, Integer> {
	
	public List<Lending>findAllByUserId(Integer id);
	
	public Lending findAllByBookId(Integer id);

}
