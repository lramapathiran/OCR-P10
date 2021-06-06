package com.lavanya.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lavanya.api.model.Notification;
import com.lavanya.api.service.BatchService;

/**
 * Rest Controller to control all the requests related to Notification object.
 * @author lavanya
 */
@RestController
public class BatchController {
	
	@Autowired
	BatchService batchService;
	
	/**
	 * GET requests for /notifications.
	 * 
	 * @return list of Notifications object which is the list of users that have at least one lending not returned passed the due date.
	 */
	@GetMapping("/notifications")
	public List<Notification> getListOfUsersToWarn(){
		
		List<Notification> notifications = new ArrayList<>();
		
		notifications = batchService.getListOfUserNotInTime();
		
		if(notifications == null) {
			System.out.println("Aucun retard enregistré!");
		}
		
		return notifications;
	}

	/**
	 * GET requests for /notifications/reservation.
	 *
	 * @return list of Notifications object which is the list of users that have a book pre-booked ready to lend and are in position 1 in waiting list.
	 */
	@GetMapping("/notifications/reservation")
	public List<Notification> getListOfUsersToWarnForBooksAvailable() {

		List<Notification> notifications = new ArrayList<>();

		notifications = batchService.getListOfUsersWithBooksReadyToLend();

		if(notifications == null) {
			System.out.println("Aucun ouvrage pré-réservé disponible à l'emprunt!");
		}

		return notifications;

	}

}
