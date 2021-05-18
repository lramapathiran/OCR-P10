package com.lavanya.api.controller;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.lavanya.api.dto.PreBookingDto;
import com.lavanya.api.error.SaveBookingFailed;
import com.lavanya.api.model.PreBooking;
import com.lavanya.api.model.Book;
import com.lavanya.api.model.User;
import com.lavanya.api.service.BookService;
import com.lavanya.api.service.PreBookingService;
import com.lavanya.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
public class PreBookingController {

    @Autowired
    UserService userService;

    @Autowired
    PreBookingService preBookingService;

    @Autowired
    BookService bookService;

//    private static PreBookingMapper mapper = new PreBookingMapperImpl();

    /**
     * GET requests for /user/prebookings endpoint.
     * This controller-method retrieves from database all pre-bookings a authenticated user made.
     * @return List<PreBooking>.
     */
    @GetMapping("user/prebookings")
    public List<PreBookingDto> showListOfUserPreBookings(){

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        try {

            User user = userService.findUserByUsername(username);

            return preBookingService.getListOfPreBookingByUserId(user.getId());

        } catch (JWTDecodeException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * POST requests for /user/preBooking endpoint.
     * This controller-method is part of CRUD and is used to save in database PreBooking object.
     * @param preBookingDto is an instance of PreBookingDto and contains all data that need to be transferred to PreBooking
     * created Prebooking object will then be saved in DataBase.
     */
    @PostMapping(value = "/user/preBooking", consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public void savePreBooking(@RequestBody PreBookingDto preBookingDto) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();

//        PreBooking preBooking = mapper.preBookingDtoToPreBooking(preBookingDto);
        User user = userService.findUserByUsername(username);
        try{
            preBookingService.save(preBookingDto, user);
        }catch(Exception e){
            throw new SaveBookingFailed("Un exemplaire de cette ouvrage est déjà en cours d'emprunt sous votre nom, vous ne pouvez le réserver!");
        }
    }

//    @GetMapping("user/prebookings/bookId")
//    public Integer getAmountPreBooking(){
//
//        int bookId = 2;
//        Optional<Book> book = bookService.getBookById(bookId);
//
//        return preBookingService.getTotalPrebookingByBookId(book);
//    }

    /**
     * GET requests for /user/prebookings endpoint.
     * This controller-method retrieves from database all pre-bookings a authenticated user made.
     * @return List<PreBooking>.
     */
    @GetMapping("book/prebookings")
    public List<PreBookingDto> showListOfPreBookingsByBookId(){

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        try {

            User user = userService.findUserByUsername(username);

            return preBookingService.getListOfPreBookingByBookId(1);

        } catch (JWTDecodeException e){
            throw new RuntimeException(e);
        }
    }


}
