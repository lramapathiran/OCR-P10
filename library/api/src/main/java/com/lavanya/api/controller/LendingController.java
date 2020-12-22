package com.lavanya.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lavanya.api.model.Lending;
import com.lavanya.api.service.LendingService;

/**
 * Rest Controller used in MVC architecture to control all the requests related to Lending object.
 * @author lavanya
 */
@RestController
public class LendingController {
	
	@Autowired
	LendingService lendingService;
	
	/**
     * GET requests for /lending endpoint.
     * This controller-method retrieves details on a lending of interest.  
     * 
     * @param bookId an int to specify the id of the Book object borrowed by the user.
     * @return Lending object containing details on the lending.
     */	
	@GetMapping("/user/lending")
	public	Lending getLendingDetails(@RequestParam ("bookId") int bookId) {
		
		return lendingService.getLendingByBookId(bookId);		
		
	}
	
	/**
     * GET requests for /user/lendings endpoint.
     * This controller-method retrieves the list of books borrowed by the user connected.  
     * 
     * @param userId an int to specify the id of the user connected.
     * @return the list of books borrowed with all the details.
     */	
	@GetMapping("/user/lendings")
	public List<Lending> showListOfUserLendings(@RequestParam("userId") int userId){
		
		return lendingService.getListOfLendingByUserId(userId);
	}
}
