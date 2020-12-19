package com.curatorsodisha.web.ui.shared;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.curatorsodisha.web.ui.constants.ValidationMessages;

public class UsersDTO{
	
	
	private String userId;
	
	@NotNull(message = ValidationMessages.NONNULL_FIRSTNAME)
	@Size(min = 2, message = ValidationMessages.INVALID_FIRSTNAME)
	private String firstName;
	
	@NotNull(message = ValidationMessages.NONNULL_LASTNAME)
	@Size(min = 2, message = ValidationMessages.INVALID_LASTNAME)
	private String lastName;
	
	@NotNull(message = ValidationMessages.NONNULL_EMAIL)
	@Email(message = ValidationMessages.INVALID_EMAIL)
	private String email;
	
	
	private long contactNumber;


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public long getContactNumber() {
		return contactNumber;
	}


	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	
	

}
