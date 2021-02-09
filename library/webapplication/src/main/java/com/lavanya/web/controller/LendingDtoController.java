package com.lavanya.web.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lavanya.web.dto.LendingDto;
import com.lavanya.web.dto.UserDto;
import com.lavanya.web.proxies.LendingProxy;
import com.lavanya.web.proxies.UserProxy;

/**
 * Controller used in MVC architecture to control all the requests related to LendingDto object.
 * @author lavanya
 */
@Controller
public class LendingDtoController {
	
	@Autowired
	LendingProxy lendingProxy;
	
	@Autowired
	UserProxy userProxy;
	
	/**
     * POST requests for /user/lendingToSave endpoint.
     * This controller-method is part of CRUD and is used to communicate to the api module and save in database Lending object using the dto LendingDto.
     * @param lendingDto is an instance of LendingDto and contains all data to transfer to Lending object that need to be saved.
     * @param userConnected is the authenticated User passed within the object MyUserDetails
     * @return redirect to lending.html, a confirmation lending page.
     */	
	 @PostMapping("/user/lendingToSave")
	 public String bookLending(@RequestParam (value="userId") Integer userId,LendingDto lendingDto){
		 
		 lendingProxy.saveLending(lendingDto);
		  
	     return "redirect:/user/lending?userId=" + userId;
	 }
	 
	 /**
	  * GET requests for /user/lending endpoint.
	  * This controller-method retrieves from database the lending a user has just made in order to confirm that the process of a book lending has been made successfully. 
	  * Data related to this lending are then passed to the model and displayed in the view "lending.html".
	  * 
	  * @param model to pass data to the view.
	  * @param userConnected is the authenticated User passed within the object MyUserDetails.
	  * @return lending.html
	  */	
//	 @GetMapping("/user/lending")
//	 public String showLendingConfirmation(@RequestParam(value="userId") int userId, Model model){
//		 LendingDto lendingDto = lendingProxy.getLendingDetails(userId);
//		 model.addAttribute("lendingDto", lendingDto);
//		 model.addAttribute("userId", userId);
//		 return "lending";
//	 }
	 
	 /**
	  * GET requests for /user/lendings endpoint.
	  * This controller-method retrieves from database all lendings a authenticated user made and pass that list to the view "userDashboard.html" 
	  * @param model to pass data to the view.
	  * @param userConnected is the authenticated User passed within the object MyUserDetails
	  * @return userDashboard.html
	  */	
	 @GetMapping("/user/lendings")
	 public String showUserLendingsList(HttpSession session, Model model){
		 
		 if(session==null) {
			 return "redirect:/index";
		 }
		 String token = (String) session.getAttribute("token");
		 String subToken = token.substring(7);
		 try {
			    DecodedJWT jwt = JWT.decode(subToken);
			    String username = jwt.getSubject();
			    model.addAttribute("username", username);
			} catch (JWTDecodeException e){
				throw new RuntimeException(e);
			}
		 

//		 Optional<UserDto> userConnected = userProxy.getUserConnected(userId);
			
//		if( userConnected.isPresent() ) {
//	         model.addAttribute("user", userConnected.get());
//		}
		 List<LendingDto> booksList = lendingProxy.showListOfUserLendings(token);
//		 model.addAttribute("userId",userId);
	     model.addAttribute("list", booksList);

	     return "userDashboard";
	 }
	 
	 /**
	  * POST requests for /user/lending/extendDate endpoint.
	  * This controller-method is used to extend the return date by 4 weeks of a lending.
	  * @param userConnected is the authenticated User passed within the object MyUserDetails.
	  * @return redirect to userDashboard.html, a confirmation lending page.
	  */	
	 @PostMapping("/user/lending/extendDate")
	 public String getExtension(Integer lendingDtoId, @RequestParam ("userId") int userId) {		 
		 
		 lendingProxy.updateLending(lendingDtoId);
		 
		 return "redirect:/user/lendings?userId=" + userId;
	 }
}
