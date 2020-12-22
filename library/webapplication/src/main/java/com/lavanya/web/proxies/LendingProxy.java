package com.lavanya.web.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lavanya.web.dto.LendingDto;

@FeignClient(name = "api", url = "localhost:9090")
public interface LendingProxy {

	@GetMapping(value = "/user/lending")
	LendingDto getLendingDetails(@RequestParam(value="bookId") int bookId);

	@GetMapping( value = "/user/lendings")
	List<LendingDto> showListOfUserLendings(@RequestParam int userId);
	
}
