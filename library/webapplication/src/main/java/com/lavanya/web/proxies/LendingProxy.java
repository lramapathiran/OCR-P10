package com.lavanya.web.proxies;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lavanya.web.dto.LendingDto;

@FeignClient(contextId = "lendingClient", name = "api", url = "localhost:9090")
public interface LendingProxy {

	@GetMapping(value = "/user/lending", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	LendingDto getLendingDetails(@RequestParam(value="bookId") int bookId);

	@GetMapping(value = "/user/lendings")
	List<LendingDto> showListOfUserLendings(@RequestParam int userId);
	
	@PutMapping(value="/user/lending/extendDate/{id}")
	LendingDto updateLending(LendingDto lending, @PathVariable ("id") Integer lendingId);
	
//	@PostMapping(value="/user/extendBookDueDate")
	
	
}
