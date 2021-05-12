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

    @PostMapping("/preBooking")
    public String preBooking(BookDto bookDto, HttpSession session) {
        String token = (String) session.getAttribute("token");

        if(token==null) {
            return "redirect:/homePage#sign-in";
        }

        PreBookingDto preBookingDto = new PreBookingDto();
        preBookingDto.setBookDto(bookDto);
        preBookingProxy.savePreBooking(preBookingDto, token);

        return "redirect:/showBooks/{pageNumber}";
    }

}
