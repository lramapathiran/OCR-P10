package com.lavanya.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lavanya.web.dto.LendingDto;
import com.lavanya.web.proxies.LendingProxy;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Controller used in MVC architecture to control all the requests related to LendingDto object.
 * @author lavanya
 */
@Controller
public class LendingDtoController {
	
	@Autowired
	LendingProxy lendingProxy;
	 
	 /**
	  * GET requests for /user/lendings endpoint.
	  * This controller-method retrieves from database all lendings a authenticated user made and pass that list to the view "userDashboard.html" 
	  * @param model to pass data to the view.
	  * @param session a HttpSession where attributes of interest are stored, here it concerns the token generated following user connection.
	  * @return userDashboard.html
	  */	
	 @GetMapping("/user/lendings")
	 public String showUserLendingsList(@RequestParam(value = "error", required = false) String error, HttpSession session, Model model){
	
		 String token = (String) session.getAttribute("token");
		 
		 if(token==null) {
			 return "redirect:/homePage#sign-in";
		 }
		 
		 String subToken = token.substring(7);
		 try {
			    DecodedJWT jwt = JWT.decode(subToken);
				String fullname = jwt.getClaim("fullname").asString();
							 
				model.addAttribute("fullname", fullname);
				
			} catch (JWTDecodeException e){
				throw new RuntimeException(e);
			}

		 String errorMessage = null;
		 if(error != null) {
			 errorMessage = "La date limite de retour est dépassée, vous n'êtes plus autorisé à la prolonger!";
		 }
		 model.addAttribute("errorMessage", errorMessage);
		 
		 List<LendingDto> booksList = lendingProxy.showListOfUserLendings(token);
	     model.addAttribute("list", booksList);

	     return "userDashboard";
	 }
	 
	 /**
	  * POST requests for /user/lending/extendDate endpoint.
	  * This controller-method is used to extend the return date by 4 weeks of a lending.
	  * @param lendingDtoId id of the LendingDto object which has its due date extended of 4 weeks.
	  * @param session a HttpSession where attributes of interest are stored, here it concerns the token generated following user connection.
	  * @return redirect to userDashboard.html, a confirmation lending page.
	  */	
	 @PostMapping("/user/lending/extendDate")
	 public String getExtension(Integer lendingDtoId, HttpSession session) {	
		 
		 String token = (String) session.getAttribute("token");
		 
		 if(token==null) {
			 return "redirect:/homePage#sign-in";
		 }

		 try{
			 lendingProxy.updateLending(lendingDtoId, token);
			 return "redirect:/user/lendings";
		 }catch(Exception e){
		 	return "redirect:/user/lendings?error=true";
		 }

		 

	 }
}
