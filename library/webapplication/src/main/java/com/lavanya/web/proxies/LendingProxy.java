package com.lavanya.web.proxies;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.lavanya.web.dto.LendingDto;
import com.lavanya.web.dto.BookDto;

/**
 * interface required to communicate with api module and make all the requests related to Lending object.
 * @author lavanya
 */
@FeignClient(name = "lendingApi", url = "localhost:9090")
public interface LendingProxy {

	@GetMapping(value = "/user/lendings")
	List<LendingDto> showListOfUserLendings(@RequestHeader(name = "Authorization") String token);
	
	@PostMapping(value="/user/lending/extendDate/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	void updateLending(@PathVariable ("id") Integer lendingId, @RequestHeader(name = "Authorization") String token);	

	@PostMapping(value="/lending/dueDate")
	LocalDate showDueDateByBookId(@RequestBody BookDto bookDto, @RequestHeader(name = "Authorization") String token);
}
