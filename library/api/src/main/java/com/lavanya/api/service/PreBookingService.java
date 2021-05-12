package com.lavanya.api.service;

import com.lavanya.api.error.SaveBookingFailed;
import com.lavanya.api.error.SaveLendingFailed;
import com.lavanya.api.model.Book;
import com.lavanya.api.model.PreBooking;
import com.lavanya.api.model.User;
import com.lavanya.api.repository.PreBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service provider for all business functionalities related to PreBooking class.
 * @author lavanya
 */
@Service
public class PreBookingService {

    @Autowired
    BookService bookService;

    @Autowired
    PreBookingRepository preBookingRepository;


    /**
     * method to retrieve all pre-bookings made by a user of interest from database.
     * @param userId id of user of interest for whom belongs the list of pre-bookings.
     * @return list of PreBooking.
     */
    public List<PreBooking> getListOfPreBookingByUserId(int userId){

        return preBookingRepository.findAllByUserIdOrderByTime(userId);

    }

    public PreBooking save(PreBooking preBooking, User user) {

        preBooking.setUser(user);
        preBooking.setTime(LocalDateTime.now());

//        Integer id = preBooking.getBook().getId();


        PreBooking preBookingSaved = preBookingRepository.save(preBooking);

//        Integer preBookingId = preBooking.getId();
//        if(preBookingId==null) {
//            throw new SaveBookingFailed(
//                    "L'emprunt a échoué, veuillez recommencer");
//        }

//        bookService.updateBookPreBooked(id);


        return preBookingSaved;

    }
}
