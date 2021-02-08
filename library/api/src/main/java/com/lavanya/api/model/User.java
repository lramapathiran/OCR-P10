package com.lavanya.api.model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Bean representing an User.
 * User object is declared as a JPA entity with the corresponding annotation.
 * @author lavanya
 */
@Entity
@Table(	name = "user")
public class User {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	Integer id;
	
	@Column(name="first_name")
	public String firstName;
	
	@Column(name="last_name")
	public String lastName;
	
	@Column(name="date_of_birth")
	private LocalDate dateOfBirth;
	
	private String address;
	private String telephone;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	private boolean enabled;
	
	private String roles;
	
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Lending> lending;
	
	public User() {
	}
	
	public User(String username, String email, String password, String firstName, String lastName,boolean enabled,String role, Integer id) {
		this.username = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.enabled = isEnabled();
		this.id = id;
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

	public List<Lending> getLending() {
		return lending;
	}

	public void setLending(List<Lending> lending) {
		this.lending = lending;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", address=" + address + ", telephone=" + telephone + ", email=" + email + ", username=" + username
				+ ", password=" + password + ", enabled=" + enabled + ", roles="
				+ roles + ", lending=" + lending + "]";
	}

	
	
}
