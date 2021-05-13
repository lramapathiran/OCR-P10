package com.lavanya.web.controller;

import com.lavanya.web.dto.BookDto;
import com.lavanya.web.dto.PreBookingDto;
import com.lavanya.web.proxies.PreBookingProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * Controller used in MVC architecture to control all the requests related to PreBookingDto object.
 * @author lavanya
 */
@Controller
public class PreBookingDtoController {

    @Autowired
    PreBookingProxy preBookingProxy;

    /**
     * POST requests for /preBooking endpoint.
     * This controller-method is part of CRUD and is used to save in database PreBooking object.
     * @param bookDto is the book that needs to be pre-booked.
     * @param session a HttpSession where attributes of interest are stored, here it concerns the token generated following user connection.
     * @return lending saved by a user connected.
     */
    @PostMapping("/preBooking")
    public String preBooking(BookDto bookDto, HttpSession session) {
        String token = (String) session.getAttribute("token");

        if(token==null) {
            return "redirect:/homePage#sign-in";
        }


        try{
            PreBookingDto preBookingDto = new PreBookingDto();
            preBookingDto.setBookDto(bookDto);
            preBookingProxy.savePreBooking(preBookingDto, token);

            return "redirect:/user/lendings";
        }catch(Exception e){
            return "redirect:/showBooks/1?error=true";
        }

    }

}
