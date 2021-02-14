package com.lavanya.batch.email;

public interface EmailService {
	
	void sendSimpleMessage(String to,
            String subject,
            String text);
}
