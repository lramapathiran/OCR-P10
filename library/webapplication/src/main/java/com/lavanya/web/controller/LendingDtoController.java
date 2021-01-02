package com.lavanya.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lavanya.web.dto.LendingDto;
import com.lavanya.web.proxies.LendingProxy;

@Controller
public class LendingDtoController {
	
	@Autowired
	LendingProxy lendingProxy;
	
	 @RequestMapping("/user/lending")
	 public String showLending(@RequestParam ("bookId") int bookId, Model model){

		 LendingDto lendingDto = lendingProxy.getLendingDetails(bookId);

	     model.addAttribute("lending", lendingDto);

	     return "lending";
	 }

	 @RequestMapping("/user/lendings")
	 public String showUserLendingsList(@RequestParam ("userId") int userId, Model model){

		 List<LendingDto> booksList = lendingProxy.showListOfUserLendings(userId);

	     model.addAttribute("list", booksList);

	     return "userDashboard";
	 }
	 
	 @PostMapping(value="user/lending/extendDate/{id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	 public LendingDto getExtension( LendingDto lending,@PathVariable ("id") Integer lendingDtoId) {		 
		 
		 return lendingProxy.updateLending(lending, lendingDtoId);
	 }
}
