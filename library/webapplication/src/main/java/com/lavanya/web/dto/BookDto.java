package com.lavanya.web.dto;

import java.util.List;

public class BookDto {
	
	Integer id;	
	String title;
	String author;
	Integer remainingStock;
	Integer fullStock;
	private List<LendingDto> lendingDtos;
	
	public BookDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getRemainingStock() {
		return remainingStock;
	}

	public void setRemainingStock(Integer remainingStock) {
		this.remainingStock = remainingStock;
	}

	public Integer getFullStock() {
		return fullStock;
	}

	public void setFullStock(Integer fullStock) {
		this.fullStock = fullStock;
	}

	public List<LendingDto> getLendings() {
		return lendingDtos;
	}

	public void setLendings(List<LendingDto> lendingDtos) {
		this.lendingDtos = lendingDtos;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", remainingStock=" + remainingStock
				+ ", fullStock=" + fullStock + ", lendings=" + lendingDtos + "]";
	}
	
}
