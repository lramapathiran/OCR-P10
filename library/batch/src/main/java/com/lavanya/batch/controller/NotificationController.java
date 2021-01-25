package com.lavanya.batch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.lavanya.batch.dto.NotificationDto;
import com.lavanya.batch.proxies.NotificationProxy;

@Controller
public class NotificationController {
	
	@Autowired
	NotificationProxy notificationProxy;
	
	@Scheduled(cron = "${cron.expression}")
	@GetMapping("/alerte")
	public void sendNotifications() {
		List<NotificationDto> list = notificationProxy.getListOfUsersToWarn();
		long now = System.currentTimeMillis() / 1000;
		if(list != null) { 
			for(NotificationDto notice:list) {
				String name = notice.getFullId();
				String title = notice.getTitle();
				String author = notice.getAuthor();
				System.out.println("Avertissement" + now);
				System.out.println("Bonjour " + name + "!");
				System.out.println("La date retour de l'ouvrage " + title + ", " + author + " est dépassée.");
				System.out.println("Merci de le retrouner au plus vite!");
			}
		}
		else {
			System.out.println("Aucun utilisateur n'a de retard dans les retours de ses livres!");
		}
	}

}
