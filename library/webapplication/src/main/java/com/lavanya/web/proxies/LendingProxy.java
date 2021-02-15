package com.lavanya.web.proxies;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.lavanya.web.dto.LendingDto;

/**
 * interface required to communicate with api module and make all the requests related to Lending object.
 * @author lavanya
 */
@FeignClient(name = "lendingApi", url = "localhost:9090")
public interface LendingProxy {

	@GetMapping(value = "/user/lending", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	Optional<LendingDto> getLendingDetails(@RequestHeader(name = "Authorization") String token, @RequestParam(value="id") Integer lendingDtoId);

	@GetMapping(value = "/user/lendings")
	List<LendingDto> showListOfUserLendings(@RequestHeader(name = "Authorization") String token);
	
	@PostMapping(value="/user/lending/extendDate/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	void updateLending(@PathVariable ("id") Integer lendingId, @RequestHeader(name = "Authorization") String token);
	
	@PostMapping(value="/user/save/lending", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	LendingDto saveLending(@RequestBody LendingDto lending, @RequestHeader(name = "Authorization") String token);
	
	
	
	
}
