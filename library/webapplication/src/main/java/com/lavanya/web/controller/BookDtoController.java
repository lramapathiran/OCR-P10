package com.lavanya.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;

import com.lavanya.web.dto.BookDto;
import com.lavanya.web.dto.LendingDto;
import com.lavanya.web.dto.UserDto;
import com.lavanya.web.proxies.BookProxy;
import com.lavanya.web.proxies.UserProxy;

/**
 * Controller used in MVC architecture to control all the requests related to BookDto object.
 * @author lavanya
 */
@Controller
public class BookDtoController {
	
	@Autowired
	BookProxy bookProxy;
	
	@Autowired
	UserProxy userProxy;
	
	
	/**
     * GET requests for /showBooks/{pageNumber} endpoint.
     * This controller-method retrieves all books of the library, display them as Page to the view "searchBook".
     * It offers the possibility to search a book with filters and to borrow one of them if the book is in stock.
     * 
     * @param model to pass data to the view.
     * @param currentPage an int to specify which page of Books to be displayed.
     * @param keyword a String attribute from Search object used to filter a search books by keyword.
     * @param userConnected is the authenticated User passed within the object MyUserDetails
     * @return "searchBook.html".
     */	
	@GetMapping("/showBooks/{pageNumber}")
	public String showBooksListFiltered(@PathVariable(value = "pageNumber") int currentPage, @RequestParam ("userId") Integer userId,
			@RequestParam(name="keyword", required=false) String keyword, Model model) {
		
		LendingDto lendingDto = new LendingDto();
		
		Optional<UserDto> userConnected = userProxy.getUserConnected(userId);
		
		if( userConnected.isPresent() ) {
            model.addAttribute("user", userConnected.get());
		}
		
		Page<BookDto> pageOfBooksFiltered = bookProxy.getBookSearchPage(userId, currentPage, keyword);
		
		model.addAttribute("keyword", keyword);
		
		List<BookDto> booksPage = pageOfBooksFiltered.getContent();
		int totalPages = pageOfBooksFiltered.getTotalPages();
		long totalBooks = pageOfBooksFiltered.getTotalElements();
		
		model.addAttribute("userId", userId);
		model.addAttribute("booksPage", booksPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalBooks", totalBooks);
		model.addAttribute("lendingDto", lendingDto);
		
		return "searchBook";
	}
}
