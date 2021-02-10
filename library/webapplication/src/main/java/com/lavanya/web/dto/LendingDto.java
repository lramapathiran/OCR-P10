package com.lavanya.web.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Bean representing a data transfer Object LendingDto.
 * LendingDto has all attributes required for lending a book.
 * @author lavanya
 */
public class LendingDto {
	
	Integer id;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	LocalDate lendingDate;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	LocalDate dueDate;
	
	Boolean isExtended;	
	
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

	public BookDto getBook() {
		return bookDto;
	}

	public void setBook(BookDto bookDto) {
		this.bookDto = bookDto;
	}

	@Override
	public String toString() {
		return "Lending [id=" + id + ", lendingDate=" + lendingDate + ", dueDate=" + dueDate + ", isExtended="
				+ isExtended + ", book=" + bookDto + "]";
	}

}
