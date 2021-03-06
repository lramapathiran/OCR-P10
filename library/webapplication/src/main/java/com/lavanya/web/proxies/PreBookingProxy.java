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

    @GetMapping(value = "user/prebookings")
    List<PreBookingDto> showListOfUserPreBookings(@RequestHeader(name = "Authorization") String token);

//    @PostMapping(value="/user/preBooking/{id}", consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
//    void updateLending(@PathVariable("id") Integer lendingId, @RequestHeader(name = "Authorization") String token);

    @PostMapping(value = "/user/preBooking", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    void savePreBooking(@RequestBody PreBookingDto preBookingDto, @RequestHeader(name = "Authorization") String token);

    @GetMapping(value = "/book/prebookings")
    List<PreBookingDto> showListOfPreBookingsByBookId(@RequestParam("id") int bookId, @RequestHeader(name = "Authorization") String token);

    @PostMapping("/preBooking/delete")
    public void deletePreBooking(@RequestParam (value="id") int id, @RequestHeader(name = "Authorization") String token);
}