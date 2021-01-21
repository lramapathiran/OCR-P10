package com.lavanya.api.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "user", 
uniqueConstraints = { 
	@UniqueConstraint(columnNames = "username"),
	@UniqueConstraint(columnNames = "email") 
})
public class User {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	Integer id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="date_of_birth")
	private LocalDate dateOfBirth;
	
	private String address;
	private String telephone;
	
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
	@NotBlank
	@Size(max = 20)
	private String username;
	
	@NotBlank
	@Size(max = 10)
	private String password;
	
//	private String encodedPassword;
	private String roles;
	
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Lending> lending;
	
	public User() {
	}
	
	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
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
//
//	public String getEncodedPassword() {
//		return encodedPassword;
//	}
//
//	public void setEncodedPassword(String encodedPassword) {
//		this.encodedPassword = encodedPassword;
//	}
	
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

//	@Override
//	public String toString() {
//		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
//				+ ", address=" + address + ", telephone=" + telephone + ", email=" + email + ", username=" + username
//				+ ", password=" + password + ", encodedPassword=" + encodedPassword + ", roles=" + roles + ", lending="
//				+ lending + "]";
//	}
	

}
