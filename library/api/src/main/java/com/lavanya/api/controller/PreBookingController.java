package com.lavanya.api.controller;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.lavanya.api.dto.PreBookingDto;
import com.lavanya.api.mapper.PreBookingMapper;
import com.lavanya.api.mapper.PreBookingMapperImpl;
import com.lavanya.api.model.PreBooking;
import com.lavanya.api.model.Book;
import com.lavanya.api.model.User;
import com.lavanya.api.service.PreBookingService;
import com.lavanya.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
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

    private static PreBookingMapper mapper = new PreBookingMapperImpl();

    /**
     * GET requests for /user/prebookings endpoint.
     * This controller-method retrieves from database all pre-bookings a authenticated user made.
     * @return List<PreBooking>.
     */
    @GetMapping("user/prebookings")
    public List<PreBooking> showListOfUserPreBookings(){

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        try {

            User user = userService.findUserByUsername(username);

            return preBookingService.getListOfPreBookingByUserId(user.getId());

        } catch (JWTDecodeException e){
            throw new RuntimeException(e);
        }
    }


    @PostMapping(value = "/user/preBooking", consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public void savePreBooking(@RequestBody PreBookingDto preBookingDto) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        PreBooking preBooking = mapper.preBookingDtoToPreBooking(preBookingDto);
        User user = userService.findUserByUsername(username);

        PreBooking preBookingSaved = preBookingService.save(preBooking, user);
    }


}
