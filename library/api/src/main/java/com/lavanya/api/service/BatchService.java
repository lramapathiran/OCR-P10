package com.lavanya.api.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lavanya.api.model.Lending;
import com.lavanya.api.model.User;
import com.lavanya.api.model.Notification;
import com.lavanya.api.repository.BatchRepository;

@Service
public class BatchService {
	
	@Autowired
	BatchRepository batchRepository;
	
	public List<Lending> getLendingsNotReturned() {
		
		LocalDate todayDate = LocalDate.now();
		return batchRepository.getLendingsNotInTime(todayDate);
		
	}
	
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
