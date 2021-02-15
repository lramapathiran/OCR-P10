package com.lavanya.api.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lavanya.api.model.Lending;
import com.lavanya.api.model.User;
import com.lavanya.api.model.Notification;
import com.lavanya.api.repository.BatchRepository;

/**
 * Service provider for all business functionalities related to Notification class.
 * @author lavanya
 */
@Service
public class BatchService {
	
	@Autowired
	BatchRepository batchRepository;
	
	/**
	 * method to retrieve all lendings not returned on time before due date from database.
	 * @return list of Lending.
	 */
	public List<Lending> getLendingsNotReturned() {
		
		LocalDate todayDate = LocalDate.now();
		return batchRepository.getLendingsNotInTime(todayDate);
		
	}
	
	/**
	 * method to generate a list of Notification from a list of Lending not returned on time.
	 * @return list of Notification.
	 */
	public List<Notification> getListOfUserNotInTime() {
		List<Lending> lendings = getLendingsNotReturned();
		
		List<Notification> notifications = new ArrayList<>();
		
		
		if (lendings != null) {
			for (Lending lending:lendings) {
				User user = lending.getUser();
				String firstName = user.getFirstName();
				String lastName = user.getLastName();
				String fullId = firstName + " " + lastName;
				String title = lending.getBook().getTitle();
				String author = lending.getBook().getAuthor();
				String email = lending.getUser().getEmail();
				
				Notification userTowarn = new Notification(fullId, email, title, author);
				notifications.add(userTowarn);
			}
		}
		
		return notifications;
	}

}
