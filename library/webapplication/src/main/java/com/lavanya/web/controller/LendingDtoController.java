package com.lavanya.web.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lavanya.web.dto.LendingDto;
import com.lavanya.web.dto.UserDto;
import com.lavanya.web.proxies.LendingProxy;
import com.lavanya.web.proxies.UserProxy;

@Controller
public class LendingDtoController {
	
	@Autowired
	LendingProxy lendingProxy;
	
	@Autowired
	UserProxy userProxy;
	
	 @PostMapping("/user/lendingToSave")
	 public String showLending(@RequestParam (value="userId") Integer userId,LendingDto lendingDto){
		 
		 lendingProxy.saveLending(lendingDto);
		  
	     return "redirect:/user/lending?userId=" + userId;
	 }
	 
//	 @GetMapping("/user/lending")
//	 public String showLendingConfirmation(@RequestParam(value="userId") int userId, Model model){
//		 LendingDto lendingDto = lendingProxy.getLendingDetails(userId);
//		 model.addAttribute("lendingDto", lendingDto);
//		 model.addAttribute("userId", userId);
//		 return "lending";
//	 }
	 
	 @GetMapping("/user/lendings")
	 public String showUserLendingsList(Model model){
		 
//		 Optional<UserDto> userConnected = userProxy.getUserConnected(userId);
//			
//		if( userConnected.isPresent() ) {
//	         model.addAttribute("user", userConnected.get());
//		}
		 List<LendingDto> booksList = lendingProxy.showListOfUserLendings();
//		 model.addAttribute("userId",userId);
	     model.addAttribute("list", booksList);

	     return "userDashboard";
	 }
	 
	 @PostMapping("/user/lending/extendDate")
	 public String getExtension(Integer lendingDtoId, @RequestParam ("userId") int userId) {		 
		 
		 lendingProxy.updateLending(lendingDtoId);
		 
		 return "redirect:/user/lendings?userId=" + userId;
	 }
}
