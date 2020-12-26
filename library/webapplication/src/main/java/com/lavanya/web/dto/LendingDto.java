package com.lavanya.web.dto;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

public class LendingDto {
	
	Integer id;
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	String lendingDate;
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	String dueDate;
	Boolean isExtended;
	
	
	private UserDto userDto;
	
	
	private BookDto bookDto;
	
	public LendingDto() {
	}
	
//	@JsonProperty("user_id")
//	private void unpackNested(Integer user_id) {
//	    this.userDto = new UserDto();
//	    bookDto.setId(user_id);
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLendingDate() {
		return lendingDate;
	}

	public void setLendingDate(String lendingDate) {
		this.lendingDate = lendingDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public Boolean getIsExtended() {
		return isExtended;
	}

	public void setIsExtended(Boolean isExtended) {
		this.isExtended = isExtended;
	}

	public UserDto getUser() {
		return userDto;
	}

	public void setUser(UserDto userDto) {
		this.userDto = userDto;
	}

	public BookDto getBook() {
		return bookDto;
	}

	public void setBook(BookDto bookDto) {
		this.bookDto = bookDto;
	}

	@Override
	public String toString() {
		return "Lending [id=" + id + ", lendingDate=" + lendingDate + ", dueDate=" + dueDate + ", isExtended="
				+ isExtended + ", user=" + userDto + ", book=" + bookDto + "]";
	}

}
