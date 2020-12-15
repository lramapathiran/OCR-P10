package com.lavanya.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Book {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	Integer id;
	
	String title;
//	author firstName and lastName
	String author;
//	ajouter stock par livre
	Integer remainingStock;
	
	Integer fullStock;
	
	@OneToOne(mappedBy = "lending")
	private Lending lending;
	
	public Book() {
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

	public Lending getLending() {
		return lending;
	}

	public void setLending(Lending lending) {
		this.lending = lending;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", remainingStock=" + remainingStock
				+ ", fullStock=" + fullStock + ", lending=" + lending + "]";
	}
	

}
