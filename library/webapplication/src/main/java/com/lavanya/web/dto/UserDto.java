package com.lavanya.web.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Bean representing a data transfer Object UserDto.
 * UserDto has all attributes required to register an user.
 * @author lavanya
 */
public class UserDto {

	Integer id;
	

	public String firstName;

	public String lastName;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private String address;
	private String telephone;
	private String email;

	private String username;
	private String password;
	private boolean enabled;
	private String roles;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	public List<LendingDto> getLendingDto() {
		return lendingDto;
	}

	public void setLendingDto(List<LendingDto> lendingDto) {
		this.lendingDto = lendingDto;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", address=" + address + ", telephone=" + telephone + ", email=" + email + ", username="
				+ username + ", password=" + password + ", enabled=" + enabled + ", roles=" + roles + ", lendingDto="
				+ lendingDto + "]";
	}

}
