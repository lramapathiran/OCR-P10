package com.lavanya.web.dto;

import java.time.LocalDate;
import java.util.List;

public class UserDto {

	Integer id;
	

	String firstName;

	String lastName;

	LocalDate dateOfBirth;
	String address;
	String telephone;
	String email;

	String memberId;
	String password;
	String encodedPassword;

    private List<LendingDto> lendingDto;
	
	public UserDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncodedPassword() {
		return encodedPassword;
	}

	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}
	
	public List<LendingDto> getLending() {
		return lendingDto;
	}

	public void setLending(List<LendingDto> lendingDto) {
		this.lendingDto = lendingDto;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", address=" + address + ", telephone=" + telephone + ", email=" + email + ", memberId=" + memberId
				+ ", lending=" + lendingDto + "]";
	}	
}
