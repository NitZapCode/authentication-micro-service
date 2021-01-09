package com.curatorsodisha.web.shared;

import java.util.List;

public class UserLoginResponseDTO {

	private String userId;
	private String userName;
	private List<String> access;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<String> getAccess() {
		return access;
	}
	public void setAccess(List<String> access) {
		this.access = access;
	}
	
	
	
	
}
