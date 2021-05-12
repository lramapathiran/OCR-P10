package com.lavanya.web.controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import com.lavanya.web.dto.PreBookingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lavanya.web.dto.BookDto;
import com.lavanya.web.dto.LendingDto;
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
     * @param session a HttpSession where attributes of interest are stored, here it concerns the token generated following user connection.
     * @return "searchBook.html".
     */	
	@GetMapping("/showBooks/{pageNumber}")
	public String showBooksListFiltered(@PathVariable(value = "pageNumber") int currentPage, HttpSession session,
			@RequestParam(name="keyword", required=false) String keyword, Model model) {
		
		
		String token = (String) session.getAttribute("token");
		if(token==null) {
			 return "redirect:/homePage#sign-in";
		 }
		
		
		String subToken = token.substring(7);
		DecodedJWT jwt = JWT.decode(subToken);
		String fullname = jwt.getClaim("fullname").asString();
						 
		model.addAttribute("fullname", fullname);
		 
		LendingDto lendingDto = new LendingDto();
		
		Page<BookDto> pageOfBooksFiltered = bookProxy.getBookSearchPage(token, currentPage, keyword);
		
		model.addAttribute("keyword", keyword);
		
		List<BookDto> booksPage = pageOfBooksFiltered.getContent();
		int totalPages = pageOfBooksFiltered.getTotalPages();
		long totalBooks = pageOfBooksFiltered.getTotalElements();

		PreBookingDto preBookingDto = new PreBookingDto();
		
		model.addAttribute("booksPage", booksPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalBooks", totalBooks);
		model.addAttribute("lendingDto", lendingDto);
		model.addAttribute("preBookingDto", preBookingDto);
		
		return "searchBook";
	}
}
