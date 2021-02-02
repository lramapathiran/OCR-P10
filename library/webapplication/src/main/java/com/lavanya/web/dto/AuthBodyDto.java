package com.lavanya.web.dto;

public class AuthBodyDto {
	
	private String username;
    private String password;

    public AuthBodyDto() {
    	
    }
    
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
