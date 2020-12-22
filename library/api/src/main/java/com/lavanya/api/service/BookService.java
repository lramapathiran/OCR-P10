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

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	/**
	 * method to retrieve a list of books resulting after filtering and displayed with pagination.
	 * @param pageNumber, int to access to the number of Books Page to display.
	 * @param keyword, keyword used as filter element for the search of books.  
	 * @return Page of Book resulting after filtering.
	 */
	public Page<Book> getListOfBooksFiltered(int pageNumber, String keyword) {
		
		Sort sort = Sort.by("title").ascending();
		Pageable pageable = PageRequest.of(pageNumber - 1, 10, sort);

				
		Page<Book> page = bookRepository.findBooksByKeyword(pageable, keyword);
		return page;
	}
	
	public Optional<Book> getBookById(int id) {
		return bookRepository.findBookById(id);
	}
	
	

}
