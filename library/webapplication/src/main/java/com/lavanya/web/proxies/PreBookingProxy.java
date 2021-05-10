package com.lavanya.web.proxies;

import com.lavanya.web.dto.BookDto;
import com.lavanya.web.dto.PreBookingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * interface required to communicate with api module and make all the requests related to PreBooking object.
 * @author lavanya
 */
@FeignClient(name = "preBookingApi", url = "localhost:9090")
public interface PreBookingProxy {

    @GetMapping(value = "/user/lendings")
    List<PreBookingDto> showListOfUserPreBookings(@RequestHeader(name = "Authorization") String token);

//    @PostMapping(value="/user/preBooking/{id}", consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
//    void updateLending(@PathVariable("id") Integer lendingId, @RequestHeader(name = "Authorization") String token);

    @PostMapping(value="/user/preBooking", consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    void savePreBooking(BookDto bookDto, @RequestHeader(name = "Authorization") String token);
}
