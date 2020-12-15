package com.lavanya.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lavanya.web.model.Lending;
import com.lavanya.web.repository.LendingRepository;

public class LendingService {
	
	@Autowired
	LendingRepository lendingRepository;
	
	public List<Lending> getListOfLendingByUserId(int userId){
		
		return lendingRepository.findAllByUserId(userId);
		
	}

	public Lending getLendingByBookId(int bookId) {
		
		return lendingRepository.findAllByBookId(bookId);
	}
}
