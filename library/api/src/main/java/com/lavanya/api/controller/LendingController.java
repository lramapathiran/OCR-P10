package com.lavanya.api.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
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
	* @param userConnected is the authenticated User passed within the object MyUserDetails.
	* @return lending which is the last lending a user made.
	*/	
//	@GetMapping("/user/lending")
//	public Lending getLendingDetails(@RequestParam ("userId") int userId) {
//		
//		return lendingService.getLastLendingByUserId(userId);
//		
//	}
	
	/**
     * POST requests for /user/save/lending endpoint.
     * This controller-method is part of CRUD and is used to save in database Lending object.
     * @param lending is an instance of Lending and contains all data that need to be saved.
     */	
	@PostMapping("/user/save/lending")
	public	void saveLending(@RequestBody Lending lending) {
		
		lendingService.save(lending);
				
	}

	/**
	  * GET requests for /user/lendings endpoint.
	  * This controller-method retrieves from database all lendings a authenticated user made.
	  * @param userConnected is the authenticated User passed within the object MyUserDetails.
	  * @return List<Lending>.
	  */
	@GetMapping("/user/lendings")
	public List<Lending> showListOfUserLendings(@RequestHeader(name = "Authorization") String token){
		
//		String token = (String) session.getAttribute("token");
		 try {
			    DecodedJWT jwt = JWT.decode(token);
			    String username = jwt.getSubject();
			    User user = userService.findUserByUsername(username);
			    
			    return lendingService.getListOfLendingByUserId(user.getId());
			    
			} catch (JWTDecodeException e){
				throw new RuntimeException(e);
			}			
	}
	
	@PostMapping("/user/lending/extendDate/{id}")
	public void updateLending(@PathVariable ("id") Integer lendingId) {
		lendingService.getBookDueDateExtended(lendingId);
	}
}
