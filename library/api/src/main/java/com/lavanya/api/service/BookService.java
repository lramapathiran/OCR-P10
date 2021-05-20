package com.lavanya.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lavanya.api.model.Book;
import com.lavanya.api.repository.BookRepository;

/**
 * Service provider for all business functionalities related to Book class.
 * @author lavanya
 */
@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	/**
	 * method to retrieve a particular book identified by its own id.
	 * @param id of the book of interest to identify in database.
	 * @return Optional Book object.
	 */
	public Optional<Book> getBookById(int id) {
		return bookRepository.findBookById(id);
	}
	
	/**
	 * method to retrieve a list of books resulting after filtering and displayed with pagination.
	 * @param pageNumber, int to access to the number of Books Page to display.
	 * @param keyword, keyword which the search is based on to filter elements in the list of books.  
	 * @return Page of Book resulting after filtering.
	 */
	public Page<Book> getAllBooksFiltered(int pageNumber, String keyword) {
		
		Sort sort = Sort.by("title").ascending();
		Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);

				
		Page<Book> page = bookRepository.findFilteredBook(keyword, pageable);
		
		return page;
	}
	
	
	/**
	 * method to update a particular book by updating its remaining stock when borrowed.
	 * @param id of the book of interest to identify in database.
	 */
	public void updateBookStock(Integer id) {
		Optional<Book> optional = bookRepository.findBookById(id);
		optional.ifPresent(book -> {
			Integer stock = book.getRemainingStock();
			book.setRemainingStock(stock-1);
			bookRepository.save(book);
		});
	}
	

}
