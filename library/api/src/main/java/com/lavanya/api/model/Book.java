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
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Bean representing a Book.
 * Book object is declared as a JPA entity with the corresponding annotation.
 * @author lavanya
 */
@Entity
public class Book {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private Integer id;

    private String title;
    private String author;
	
	@Column(name="remaining_stock")
    private Integer remainingStock;
	
	@Column(name="full_stock")
    private Integer fullStock;

	@Column(name="total_pre_booking")
    private Integer totalPreBooking;
	
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Lending> lendings;

    @OneToMany(mappedBy = "book",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PreBooking> preBookings;
	
	public List<Lending> getLendings() {
		return lendings;
	}

	public void setLendings(List<Lending> lendings) {
		this.lendings = lendings;
	}

	public Book() {
	}

	public Book(Integer id, String title, String author, Integer remainingStock, Integer fullStock, Integer totalPreBooking) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.remainingStock = remainingStock;
		this.fullStock = fullStock;
		this.totalPreBooking = totalPreBooking;
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

	public Integer getTotalPreBooking() {
		return totalPreBooking;
	}

	public void setTotalPreBooking(Integer totalPreBooking) {
		this.totalPreBooking = totalPreBooking;
	}

    public List<PreBooking> getPreBookings() {
        return preBookings;
    }

    public void setPreBookings(List<PreBooking> preBookings) {
        this.preBookings = preBookings;
    }


}
