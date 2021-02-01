package com.nitzapcode.web.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

public class UserRegisterRequestDTO {

	@NotNull
	@Size(min = 2, message = "First Name must have minimum of 2 characters")
	private String firstName;
	
	@NotNull
	@Size(min = 2, message = "Last Name must have minimum of 2 characters")
	private String lastName;
	
	@NotNull
	@Email(message = "Email is not a valid one")
	private String email;
	
	@NumberFormat
	@Size(min=10, max=10, message="")
	private Long contact;
	
	@NotNull
	private String userName;
	
	@NotNull
	private String password;
	
	@NotNull
	private String role;
	
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Long getContact() {
		return contact;
	}
	
	public void setContact(Long contact) {
		this.contact = contact;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
