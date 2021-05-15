package com.lavanya.api.service;

import com.lavanya.api.dto.PreBookingDto;
import com.lavanya.api.error.SaveBookingFailed;
import com.lavanya.api.error.SaveLendingFailed;
import com.lavanya.api.model.Book;
import com.lavanya.api.model.Lending;
import com.lavanya.api.model.PreBooking;
import com.lavanya.api.model.User;
import com.lavanya.api.repository.BookRepository;
import com.lavanya.api.repository.PreBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lavanya.api.mapper.PreBookingMapper;
import com.lavanya.api.mapper.PreBookingMapperImpl;

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

    @Autowired
    BookRepository bookRepository;

    private static PreBookingMapper mapper = new PreBookingMapperImpl();

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
     * @param preBookingDto is an instance of PreBookingDto and contains all data that need to be transferred to PreBooking.
     * @param user that makes the prebooking.
     * created Prebooking object will then be saved in DataBase.
     * @return PreBooking saved.
     */
    public PreBooking save(PreBookingDto preBookingDto, User user) {

        PreBooking preBooking = mapper.preBookingDtoToPreBooking(preBookingDto);
        preBooking.setUser(user);
        preBooking.setTime(LocalDateTime.now());
        Book book = preBooking.getBook();

        Integer bookId = book.getId();
        Integer userId = user.getId();
        Optional<Lending> lending = lendingService.getLendingByUserIdAndBookId(userId,bookId);

        try{
            if(!lending.isPresent()){
                PreBooking preBookingSaved = preBookingRepository.save(preBooking);
                book.setTotalPreBooking(book.getTotalPreBooking() + 1);

                bookRepository.save(book);

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

    public Integer getTotalPrebookingByBookId(Book book){
       return preBookingRepository.numberOfPreBookingByBookId(book);
    }
}
