package com.lavanya.web.proxies;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lavanya.web.dto.AuthBodyDto;
import com.lavanya.web.dto.UserDto;


@FeignClient(name = "userApi", url = "localhost:9090")
public interface UserProxy {

	@GetMapping("/user/{id}")
	Optional<UserDto> getUserConnected(@PathVariable ("id") int id);
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value="/login", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity login(@RequestBody AuthBodyDto authBody);
	
}
