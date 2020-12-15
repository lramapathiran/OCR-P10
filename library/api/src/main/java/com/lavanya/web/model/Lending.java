package com.lavanya.web.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Lending {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	Integer id;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	LocalDate lendingDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	Date dueDate;
	Boolean isExtended;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
	private User user;

	
	@OneToOne(cascade = CascadeType.ALL)
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

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
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
