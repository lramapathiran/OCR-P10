package com.lavanya.web.proxies;

import com.lavanya.web.dto.PreBookingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * interface required to communicate with api module and make all the requests related to PreBooking object.
 * @author lavanya
 */
@FeignClient(name = "preBookingApi", url = "localhost:9090")
public interface PreBookingProxy {

    @GetMapping(value = "/user/lendings")
    List<PreBookingDto> showListOfUserPreBookings(@RequestHeader(name = "Authorization") String token);

}
