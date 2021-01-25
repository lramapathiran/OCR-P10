package com.lavanya.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lavanya.api.model.Notification;
import com.lavanya.api.service.BatchService;

@RestController
public class BatchController {
	
	@Autowired
	BatchService batchService;
	
	@GetMapping("/notifications")
	public List<Notification> getListOfUsersToWarn(){
		
		List<Notification> notifications = new ArrayList<>();
		
		notifications = batchService.getListOfUserNotInTime();
		
		if(notifications == null) {
			System.out.println("Aucun retard enregistré!");
		}
		
		return notifications;
	}

}
