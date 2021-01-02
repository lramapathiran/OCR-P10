package com.lavanya.web.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
public class LendingDto {
	
	Integer id;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	LocalDate lendingDate;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	LocalDate dueDate;
	
	Boolean isExtended;
	
	
	private UserDto userDto;
	
	
	private BookDto bookDto;
	
	public LendingDto() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public LocalDate getLendingDate() {
		return lendingDate;
	}
	

	public void setLendingDate(LocalDate lendingDate) {
		this.lendingDate = lendingDate;
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(LocalDate dueDate) {
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
