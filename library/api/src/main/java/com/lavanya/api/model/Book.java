package com.lavanya.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Bean representing a Book.
 * Book object is declared as a JPA entity with the corresponding annotation.
 * @author lavanya
 */
@Entity
public class Book {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	Integer id;
	
	String title;
	String author;
	
	@Column(name="remaining_stock")
	Integer remainingStock;
	
	@Column(name="full_stock")
	Integer fullStock;
	
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Lending> lendings;
	
	public List<Lending> getLendings() {
		return lendings;
	}

	public void setLendings(List<Lending> lendings) {
		this.lendings = lendings;
	}

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

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", remainingStock=" + remainingStock
				+ ", fullStock=" + fullStock + ", lendings=" + lendings + "]";
	}

	
	
	

}
