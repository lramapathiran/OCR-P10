package com.lavanya.web.proxies;

import java.security.Principal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lavanya.web.dto.BookDto;

@FeignClient(name = "bookApi", url = "localhost:9090")
public interface BookProxy {
	
	@GetMapping("showBooks/{pageNumber}")
	Page<BookDto> getBookSearchPage(Principal principal, @PathVariable(value = "pageNumber") int currentPage,@RequestParam(name="keyword", required=false) String keyword);

}
