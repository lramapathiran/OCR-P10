package com.lavanya.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

}
