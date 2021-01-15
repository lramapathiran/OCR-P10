package com.lavanya.api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lavanya.api.error.SaveLendingFailed;
import com.lavanya.api.model.Lending;
import com.lavanya.api.repository.LendingRepository;

@Service
public class LendingService {
	
	@Autowired
	LendingRepository lendingRepository;
	
	@Autowired
	BookService bookService;
	
	public List<Lending> getListOfLendingByUserId(int userId){
		
		return lendingRepository.findAllByUserId(userId);
		
	}

	public Optional<Lending> getLendingById(int lendingId) {
		
		return lendingRepository.findById(lendingId);
	}
	
//	public Lending getLastLendingByUserId(Integer userId) {
//		return lendingRepository.findLastRecordByUserId(userId);
//	}
	
	public void getBookDueDateExtended(Integer lendingId) {
		Optional<Lending> optional = lendingRepository.findById(lendingId);
		
		optional.ifPresent(lending -> {
			lending.setDueDate(lending.getDueDate().plusWeeks(4));
			lending.setIsExtended(true); 
			lendingRepository.save(lending);
		});		
		
	}

	public void save(Lending lending) {
		
		lending.setLendingDate(LocalDate.now());
		lending.setDueDate(lending.getLendingDate().plusWeeks(4));
		lending.setIsExtended(false);
		Integer id = lending.getBook().getId();
			
		lendingRepository.save(lending);
		
		Integer lendingId = lending.getId();
		if(lendingId==null) {
			throw new SaveLendingFailed(
		              "L'emprunt a échoué, veuillez recommencer");
		}
		
		bookService.updateBookStock(id); 
	
	}
	
	public Lending getLastRecord() {
		return lendingRepository.findFirstByOrderByIdDesc();
	}
}
