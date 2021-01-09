package com.curatorsodisha.web.shared;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailsDTO {
	
	@NotNull(message="First Name cannot be null")
	@Size(min = 2, max = 100, message = "First Name cannot be less than 2 characters")
	private String firstName;
	
	@NotNull(message = "Last Name cannot be null")
	@Size(min = 2, max = 100, message = "Last Name cannot be less than 2 characters")
	private String lastName;
	
	@NotNull(message = "Eamil cannot be null")
	@Email(message = "Invalid Email format")
	private String email;
	
	@Size(min = 10, max=10 , message = "Contact Number should be 10 characters")
	private String contactNumber;
	
	private String userId;
	
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
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
