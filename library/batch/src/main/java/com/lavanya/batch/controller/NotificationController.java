package com.lavanya.batch.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lavanya.batch.dto.NotificationDto;
import com.lavanya.batch.email.EmailService;
import com.lavanya.batch.proxies.NotificationProxy;

@Controller
public class NotificationController {
	
	@Autowired
	NotificationProxy notificationProxy;
	
	@Autowired
	EmailService emailService;
	
	private static final Map<String, Map<String, String>> labels;
	
	static {
        labels = new HashMap<>();

        //Simple email
        Map<String, String> props = new HashMap<>();
        props.put("headerText", "Send Simple Email");
        props.put("messageLabel", "Message");
        props.put("additionalInfo", "");
        labels.put("send", props);
	}
	
//	@Scheduled(cron = "${cron.expression}")
//	@GetMapping("/alerte")
//	public void sendNotifications() {
//		List<NotificationDto> list = notificationProxy.getListOfUsersToWarn();
//		
//		if(list != null) { 
//			for(NotificationDto notice:list) {
//				String name = notice.getFullId();
//				String title = notice.getTitle();
//				String author = notice.getAuthor();
//				System.out.println("Avertissement");
//				System.out.println("Bonjour " + name + "!");
//				System.out.println("La date retour de l'ouvrage " + title + ", " + author + " est dépassée.");
//				System.out.println("Merci de le retourner au plus vite!");
//			}
//		}
//		else {
//			System.out.println("Aucun utilisateur n'a de retard dans les retours de ses livres!");
//		}
//	}
	
	
	@Scheduled(cron = "${cron.expression}")
	@PostMapping("/send")
    public void createMail(){
        
		List<NotificationDto> list = notificationProxy.getListOfUsersToWarn();
		if(list != null) { 
			for(NotificationDto notice:list) {
					
				String email = notice.getEmail();
				String name = notice.getFullId();
				String title = notice.getTitle();
				String author = notice.getAuthor();
				String subject ="Alerte: Vous n'avez pas rendu votre emprunt!";
				String text = "Bonjour" + name + ", \n" + 
				"La date retour de l'ouvrage " + title + ", " + author + " est dépassée. \n" +
				"Merci de le retourner au plus vite! \n" + "L'équipe de la Bibliothèque Erik Orsenna";
				
				emailService.sendSimpleMessage(email,subject, text);
				
			}
		}

    }
	
	

}
