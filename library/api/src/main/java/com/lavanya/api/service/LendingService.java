package com.lavanya.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lavanya.api.model.Lending;
import com.lavanya.api.repository.LendingRepository;

@Service
public class LendingService {
	
	@Autowired
	LendingRepository lendingRepository;
	
	public List<Lending> getListOfLendingByUserId(int userId){
		
		return lendingRepository.findAllByUserId(userId);
		
	}

	public Lending getLendingByBookId(int bookId) {
		
		return lendingRepository.findByBookId(bookId);
	}
}
