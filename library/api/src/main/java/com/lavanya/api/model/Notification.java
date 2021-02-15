package com.lavanya.api.model;

/**
 * Bean representing a Notification.
 * Notification has all attributes required to send an email notification to a specific user.
 * @author lavanya
 */
public class Notification {
	
	String  fullId;
	String email;
	String title;
	String author;
	
	public Notification() {
		
	}
	
	
	public Notification(String fullId, String email, String title,String author) {
		this.fullId = fullId;
		this.email = email;
		this.title= title;
		this.author=author;
	}
	
	public String getFullId() {
		return fullId;
	}
	public void setFullId(String fullId) {
		this.fullId = fullId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	@Override
	public String toString() {
		return "Notification [fullId=" + fullId + ", email=" + email + ", title=" + title + ", author=" + author + "]";
	}
	
}
