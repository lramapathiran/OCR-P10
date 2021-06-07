package com.lavanya.api.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.lavanya.api.dto.PreBookingDto;
import com.lavanya.api.mapper.PreBookingMapper;
import com.lavanya.api.mapper.PreBookingMapperImpl;
import com.lavanya.api.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lavanya.api.repository.BatchRepository;

/**
 * Service provider for all business functionalities related to Notification class.
 * @author lavanya
 */
@Service
public class BatchService {
	
	@Autowired
	BatchRepository batchRepository;

	@Autowired
	PreBookingService preBookingService;

	@Autowired
	NotifiedService notifiedService;

	private static PreBookingMapper mapper = new PreBookingMapperImpl();

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
		List<Lending> lendings = this.getLendingsNotReturned();
		
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

	/**
	 * method to generate a list of Notification from a list of books pre-booked, now returned and ready for a new lending.
	 * @return list of Notification.
	 */
    public List<Notification> getListOfUsersWithBooksReadyToLend() {

		List<Book> bookList = preBookingService.getListOfDistinctBooksPreBooked();
		List<Notification> notifications = new ArrayList<>();

		for (Book book: bookList
			 ) {
			int stock = book.getRemainingStock();
			if(stock > 0) {
				List<PreBookingDto> preBookingDtoList = preBookingService.getListOfPreBookingByBookId(book.getId());
				PreBooking preBooking  = mapper.preBookingDtoToPreBooking(preBookingDtoList.get(0));

				Optional<Notified> optional = notifiedService.getNotifiedByPreBookingId(preBooking.getId());

				optional.ifPresent(notified -> {
					LocalDate deadLineForLending = notified.getNotifiedDate().plusDays(2);
					if(LocalDate.now().isAfter(deadLineForLending)) {
						preBookingService.deletePreBooking(preBooking.getId());
					}
				});

				List<PreBookingDto> preBookingDtoList1 = preBookingService.getListOfPreBookingByBookId(book.getId());
				PreBooking preBooking1  = mapper.preBookingDtoToPreBooking(preBookingDtoList1.get(0));

				Optional<Notified> optional1 = notifiedService.getNotifiedByPreBookingId(preBooking1.getId());

				if(!optional1.isPresent()){
					User user = preBooking1.getUser();
					String firstName = user.getFirstName();
					String lastName = user.getLastName();
					String fullId = firstName + " " + lastName;
					String title = book.getTitle();
					String author = book.getAuthor();
					String email = user.getEmail();

					Notification userToWarn = new Notification(fullId, email, title, author);
					notifications.add(userToWarn);

					Notified notified = new Notified(user.getId(),preBooking1.getId(),LocalDate.now());
					notifiedService.saveNotified(notified);
				}
			}
		}
		return notifications;
    }
}
