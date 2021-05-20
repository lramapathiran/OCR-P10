package com.lavanya.api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.lavanya.api.error.ExtendDueDateFailed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lavanya.api.error.SaveLendingFailed;
import com.lavanya.api.model.Lending;
import com.lavanya.api.model.Book;
import com.lavanya.api.model.User;
import com.lavanya.api.repository.LendingRepository;

/**
 * Service provider for all business functionalities related to Lending class.
 * @author lavanya
 */
@Service
public class LendingService {
	
	@Autowired
	LendingRepository lendingRepository;
	
	@Autowired
	BookService bookService;
	
	/**
	 * method to retrieve all lending made by a user of interest from database.
	 * @param userId id of user of interest for whom belongs the list of lending.  
	 * @return list of Lending.
	 */
	public List<Lending> getListOfLendingByUserId(int userId){
		
		return lendingRepository.findAllByUserIdOrderByDueDate(userId);
		
	}
	
	/**
	 * method to retrieve a particular lending identified by its id.
	 * @param lendingId, id of the lending of interest to identify in database.
	 * @return Optional Lending object.
	 */
	public Optional<Lending> getLendingByUserId(Integer lendingId) {
		Optional<Lending> lending = lendingRepository.findById(lendingId);
		return lending;
	}
	
	/**
	 * method to update a particular lending by extending its dueDate of 4 weeks.
	 * @param lendingId id of the lending of interest to identify in database.
	 */
	public void getBookDueDateExtended(Integer lendingId) {
		Optional<Lending> optional = lendingRepository.findById(lendingId);
		
		optional.ifPresent(lending -> {

		    if(LocalDate.now().isAfter(lending.getDueDate())){
                throw new ExtendDueDateFailed(
                        "La date limite de retour est dépassée, vous n'êtes plus autorisé à la prolonger!");
            }else {
                lending.setDueDate(lending.getDueDate().plusWeeks(4));
                lending.setIsExtended(true);
                lendingRepository.save(lending);
            }
		});		
		
	}
	
	/**
	 * method to save an object Lending in database.
	 * @param lending object Lending to save in database.
	 * @param user associated with the lending of interest.
	 * @return Lending object saved.
	 */
	public Lending save(Lending lending, User user) {
		
		lending.setUser(user);
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
		
		return lending;
	
	}

	/**
	 * method to retrieve a particular lending identified by the bookId and the userId for which the lending was made.
	 * @param userId, id of the user who made the lending.
	 * @param bookId, id of the book which was lent.
	 * @return Optional Lending object.
	 */
    public Optional<Lending> getLendingByUserIdAndBookId(int userId, int bookId) {
		Optional<Lending> lending = lendingRepository.findByUserIdAndBookId(userId, bookId);
		return lending;
    }

	/**
	 * method to retrieve the earliest due date of lendings made for a particular book.
	 * @param book to establish which due dates of wich lendings we are looking for.
	 * @return LocalDate for the earliest due date amoung all the copies of a book borrowed.
	 */
	public LocalDate getLendingsByEarliestDueDate(Book book){
		return lendingRepository.getEarliestDueDateByBookId(book);
	}
	
}
