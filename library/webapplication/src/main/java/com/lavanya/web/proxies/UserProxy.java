package com.lavanya.web.proxies;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lavanya.web.dto.UserDto;


@FeignClient(name = "userApi", url = "localhost:9090")
public interface UserProxy {

	@GetMapping("/user/{id}")
	Optional<UserDto> getUserConnected(@PathVariable ("id") int id);
	
}
