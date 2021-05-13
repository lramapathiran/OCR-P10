package com.lavanya.api.service;

import com.lavanya.api.error.SaveBookingFailed;
import com.lavanya.api.error.SaveLendingFailed;
import com.lavanya.api.model.Book;
import com.lavanya.api.model.Lending;
import com.lavanya.api.model.PreBooking;
import com.lavanya.api.model.User;
import com.lavanya.api.repository.PreBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service provider for all business functionalities related to PreBooking class.
 * @author lavanya
 */
@Service
public class PreBookingService {

    @Autowired
    BookService bookService;

    @Autowired
    LendingService lendingService;

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

    /**
     * method to save a PreBooking for a specific book and for the user connected.
     * @param preBooking object that need to be saved in database.
     * @param user that makes the prebooking.
     * @return PreBooking saved.
     */
    public PreBooking save(PreBooking preBooking, User user) {

        preBooking.setUser(user);
        preBooking.setTime(LocalDateTime.now());

        Integer bookId = preBooking.getBook().getId();
        Integer userId = user.getId();
        Optional<Lending> lending = lendingService.getLendingByUserIdAndBookId(userId,bookId);

        try{
            if(lending == null){
                PreBooking preBookingSaved = preBookingRepository.save(preBooking);
                return preBookingSaved;
            }
        }catch(Exception e){
            throw new SaveBookingFailed("Un exemplaire de cette ouvrage est déjà en cours d'emprunt sous votre nom, vous ne pouvez le réserver!");
        }




//        Integer preBookingId = preBooking.getId();
//        if(preBookingId==null) {
//            throw new SaveBookingFailed(
//                    "L'emprunt a échoué, veuillez recommencer");
//        }

//        bookService.updateBookPreBooked(id);

        return preBooking;
    }
}
