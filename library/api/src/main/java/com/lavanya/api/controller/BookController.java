package com.lavanya.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.lavanya.api.model.Book;
import com.lavanya.api.service.BookService;

/**
 * Rest Controller to control all the requests related to Book object.
 * @author lavanya
 */
@CrossOrigin(origins = "*")
@RestController
public class BookController {
	
	@Autowired
	BookService bookService;
	
	/**
     * GET requests for /showBooks/{pageNumber} endpoint.
     * This controller-method retrieves all books of the library.
     * 
     * @param currentPage an int to specify which page of Books to be displayed.
     * @param keyword a String attribute from Search object used to filter a search books by keyword.
     * @return page of list of books.
     */	
	@GetMapping("/user/showBooks/{pageNumber}")
	public Page<Book> getBookSearchPage(@PathVariable(value = "pageNumber") int currentPage,
			@RequestParam(name="keyword", required=false) String keyword){
		
		 try {
			 Page<Book> page = bookService.getAllBooksFiltered(currentPage, keyword);
				return page;	
			    
			} catch (JWTDecodeException e){
				throw new RuntimeException(e);
			}		
			
		
	}	
		

}
