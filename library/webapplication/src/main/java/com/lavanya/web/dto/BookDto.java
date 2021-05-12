package com.lavanya.web.dto;

import java.util.List;

/**
 * Bean representing a data transfer Object BookDto.
 * BookDto has all attributes to characterized a book.
 * @author lavanya
 */
public class BookDto {
	
	private Integer id;
	private String title;
	private String author;
	private Integer remainingStock;
	private Integer fullStock;
	private List<LendingDto> lendingDtos;
	private List<PreBookingDto> preBookingDtos;
	
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

	public List<PreBookingDto> getPreBookingDtos() {
		return preBookingDtos;
	}

	public void setPreBookingDtos(List<PreBookingDto> preBookingDtos) {
		this.preBookingDtos = preBookingDtos;
	}

	@Override
	public String toString() {
		return "BookDto{" +
				"id=" + id +
				", title='" + title + '\'' +
				", author='" + author + '\'' +
				", remainingStock=" + remainingStock +
				", fullStock=" + fullStock +
				", lendingDtos=" + lendingDtos +
				", preBookingDtos=" + preBookingDtos +
				'}';
	}

}
