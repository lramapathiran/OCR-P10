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


@Controller
public class BookDtoController {
	
	@Autowired
	BookProxy bookProxy;
	
	@Autowired
	UserProxy userProxy;
	
	@GetMapping("/showBooks/{pageNumber}")
	public String showBooksListFiltered(Principal principal, @PathVariable(value = "pageNumber") int currentPage, @RequestParam ("userId") Integer userId,
			@RequestParam(name="keyword", required=false) String keyword, Model model) {
		
		LendingDto lendingDto = new LendingDto();
		
		Optional<UserDto> userConnected = userProxy.getUserConnected(userId);
		
		if( userConnected.isPresent() ) {
            model.addAttribute("user", userConnected.get());
		}
		
		Page<BookDto> pageOfBooksFiltered = bookProxy.getBookSearchPage(principal, currentPage, keyword);
		
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
	
//	@GetMapping("showAllBooks/{pageNumber}")
//	public String showBooksList(@PathVariable(value = "pageNumber") int currentPage, Model model) {
//		
//		Page<BookDto> pageOfBooks = bookProxy.getAllBooks(currentPage);
//		
//		
//		
//		List<BookDto> booksPage = pageOfBooks.getContent();
//		int totalPages = pageOfBooks.getTotalPages();
//		long totalBooks = pageOfBooks.getTotalElements();
//		
//		model.addAttribute("booksPage", booksPage);
//		model.addAttribute("currentPage", currentPage);
//		model.addAttribute("totalPages", totalPages);
//		model.addAttribute("totalBooks", totalBooks);
//		
//		return "allBooks";
//	}
	
	

}
