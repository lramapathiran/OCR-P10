package com.lavanya.api.repository;

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
	
//	@Query("select u.dueDate, u.bookId from lending u where (u.user.id=?1,u.id) in (select u.user.id, max(u.id) as u.id from lending u)")
//	public Lending findLastRecordByUserId(Integer userId);

}
