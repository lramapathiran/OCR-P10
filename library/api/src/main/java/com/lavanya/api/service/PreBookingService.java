package com.lavanya.api.service;

import com.lavanya.api.model.PreBooking;
import com.lavanya.api.repository.PreBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<PreBooking> getListOfpreBookingByUserId(int userId){

        return preBookingRepository.findAllByUserIdOrderByTimeStamp(userId);

    }

}
