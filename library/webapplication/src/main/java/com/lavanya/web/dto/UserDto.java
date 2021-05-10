package com.lavanya.web.dto;

import java.util.List;

/**
 * Bean representing a data transfer Object UserDto.
 * UserDto has all attributes required to register an user.
 * @author lavanya
 */
public class UserDto {

	Integer id;
	public String firstName;
	public String lastName;
	private String roles;

    private List<LendingDto> lendingDto;

	private List<PreBookingDto> preBookingDtos;
	
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

	public List<PreBookingDto> getPreBookingDtos() {
		return preBookingDtos;
	}

	public void setPreBookingDtos(List<PreBookingDto> preBookingDtos) {
		this.preBookingDtos = preBookingDtos;
	}

	@Override
	public String toString() {
		return "UserDto{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", roles='" + roles + '\'' +
				", lendingDto=" + lendingDto +
				", preBookingDtos=" + preBookingDtos +
				'}';
	}

}
