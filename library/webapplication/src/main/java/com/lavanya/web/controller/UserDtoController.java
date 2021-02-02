package com.lavanya.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lavanya.web.dto.AuthBodyDto;
import com.lavanya.web.proxies.UserProxy;

@Controller
public class UserDtoController {
	
	@Autowired
	UserProxy userProxy;
	
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
	
	@PostMapping("/login")
	public String sendAuthBodyForAuthentication(AuthBodyDto authBody) {
		
		userProxy.login(authBody);
		
		return "redirect:/user/lendings";
		
	}
	
	

}
