package com.lavanya.api.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Bean representing a Lending.
 * Lending object is declared as a JPA entity with the corresponding annotation.
 * @author lavanya
 */
@Entity
public class Lending {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	Integer id;
	
	@Column(name="lending_date")
	LocalDate lendingDate;
	
	@Column(name="due_date")
	LocalDate dueDate;
	
	@Column(name="is_extended")
	Boolean isExtended;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
	@JsonBackReference
	private User user;

	
	@ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
	private Book book;
	
	public Lending() {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "Lending [id=" + id + ", lendingDate=" + lendingDate + ", dueDate=" + dueDate + ", isExtended="
				+ isExtended + ", user=" + user + ", book=" + book + "]";
	}
	

}
