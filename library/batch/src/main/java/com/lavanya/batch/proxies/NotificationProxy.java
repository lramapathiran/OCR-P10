package com.lavanya.batch.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import com.lavanya.batch.dto.NotificationDto;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * interface required to communicate with api module and make all the requests related to Notification object.
 * @author lavanya
 */
@FeignClient(name = "notificationApi", url = "localhost:9090")
public interface NotificationProxy {

	@GetMapping(value = "/notifications", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	List<NotificationDto> getListOfUsersToWarn();

	@GetMapping(value = "/notifications/reservation", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	List<NotificationDto> getListOfUsersToWarnForBooksAvailable();
}