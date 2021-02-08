package com.lavanya.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lavanya.web.dto.AuthBodyDto;
import com.lavanya.web.proxies.UserProxy;

/**
 * Controller used in MVC architecture to control all the requests related to UserDto object.
 * @author lavanya
 */
@Controller
public class UserDtoController {
	
	@Autowired
	UserProxy userProxy;
	
	/**
     * GET requests for /homePage endpoint.
     * This controller-method show the homePage of the site and the login form for the user to be connected
     * 
     * @param model  to pass data to the view.
     * @return index.html
     */	
	@GetMapping("/homePage")
	public String showLoginAndHomePage (Model model) {
	  
//    	String errorMessage = null;
//        if(error != null) {
//            errorMessage = "L'identifiant ou le mot de passe est incorrect!!";
//        }
//        if(logout != null) {
//            errorMessage = "Vous vous êtes déconnecté avec succès!!";
//        }
//        model.addAttribute("errorMessage", errorMessage);
		
		AuthBodyDto authBody = new AuthBodyDto();
		model.addAttribute("authBodyDto", authBody);
        return "index.html";
	}
	
	/**
     *  POST requests for /login endpoint.
     * This controller-method send data required for user authentication to the api module.
     * 
     * @param authBody is the bean where the password and username of the user are stored to authenticate the user.
     * @return redirect to userDashboard.html
     */	
	@PostMapping("/login")
	public String sendAuthBodyForAuthentication(AuthBodyDto data) {
		
//		String username = authBody.getUsername();
//		String password = authBody.getPassword();
		
		@SuppressWarnings("rawtypes")
		ResponseEntity resp = userProxy.login(data);
		
		return "redirect:/user/lendings?rest=" + resp;
		
	}
	
	

}
