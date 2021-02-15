package com.lavanya.api.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.lavanya.api.model.Lending;
import com.lavanya.api.model.User;
import com.lavanya.api.service.LendingService;
import com.lavanya.api.service.UserService;

/**
 * Rest Controller to control all the requests related to Lending object.
 * @author lavanya
 */
@CrossOrigin(origins = "*")
@RestController
public class LendingController {
	
	@Autowired
	LendingService lendingService;
	
	@Autowired
	UserService userService;

	/**
	* GET requests for /user/lending endpoint.
	* This controller-method retrieves from database the lending a user has just made so the last lending
	* in order to confirm that the process of a book lending has been made successfully.
	* 
	* @param lendingId id of the Lending object that has just been saved by user.
	* @return lending which is the last lending a user made.
	*/	
	@GetMapping("/user/lending")
	public Optional<Lending> getLendingDetails(@RequestParam("id") Integer lendingId) {
		
		try {
		    Optional<Lending> lending = lendingService.getLendingByUserId(lendingId); 
			return lending;
			    
		} catch (JWTDecodeException e){
			throw new RuntimeException(e);
		}			
		
		
	}
	
	/**
     * POST requests for /user/save/lending endpoint.
     * This controller-method is part of CRUD and is used to save in database Lending object.
     * @param lending is an instance of Lending and contains all data that need to be saved.
     * @return lending saved by a user connected.
     */	
	@PostMapping("/user/save/lending")
	public	Lending saveLending(@RequestBody Lending lending) {
		
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		 try {
			    
			User user = userService.findUserByUsername(username);
			Lending lendingSaved = lendingService.save(lending, user);
			return lendingSaved; 
			    
		} catch (JWTDecodeException e){
			throw new RuntimeException(e);
		}			
		
		
				
	}

	/**
	  * GET requests for /user/lendings endpoint.
	  * This controller-method retrieves from database all lendings a authenticated user made.
	  * @return List<Lending>.
	  */
	@GetMapping("/user/lendings")
	public List<Lending> showListOfUserLendings(){
		
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		 try {
			    
			    User user = userService.findUserByUsername(username);
			    
			    return lendingService.getListOfLendingByUserId(user.getId());
			    
			} catch (JWTDecodeException e){
				throw new RuntimeException(e);
			}			
	}
	
	/**
	  * POST requests for /user/lending/extendDate/{id} endpoint.
	  * This controller-method is used to extend the return date by 4 weeks of a lending.
	  * @param lendingId id of the Lending object which has its due date extended of 4 weeks.
	  */	
	@PostMapping("/user/lending/extendDate/{id}")
	public void updateLending(@PathVariable ("id") Integer lendingId) {
		lendingService.getBookDueDateExtended(lendingId);
	}
}
