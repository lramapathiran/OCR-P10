package com.lavanya.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lavanya.api.model.Book;
import com.lavanya.api.service.BookService;

/**
 * Rest Controller used in MVC architecture to control all the requests related to Book object.
 * @author lavanya
 */
@RestController
public class BookController {
	
	@Autowired
	BookService bookService;
	
	/**
     * GET requests for /showBooks/{pageNumber} endpoint.
     * This controller-method retrieves all books available in library.
     * 
     * @param currentPage an int to specify the page of Books of interest.
     * @param keyword a String used to filter a search books by keyword.
     * @param userConnected is the authenticated User passed within the object MyUserDetails.
     * @return page of Book of interest.
     */	
	@GetMapping("showBooks/{pageNumber}")
	public Page<Book> getBookSearchPage(@PathVariable(value = "pageNumber") int currentPage,@RequestParam(name="keyword", required=false) String keyword){
			
		Page<Book> page = bookService.getAllBooksFiltered(currentPage, keyword);
		return page;		
		
	}	
	
	@GetMapping("showAllBooks/{pageNumber}")
	public Page<Book> getAllBooks(@PathVariable(value = "pageNumber") int currentPage){
			
		Page<Book> page = bookService.getAllBooks(currentPage);
				
		return page;		
		
	}
	

}
