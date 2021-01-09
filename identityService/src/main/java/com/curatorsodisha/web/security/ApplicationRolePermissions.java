package com.curatorsodisha.web.security;

public enum ApplicationRolePermissions {
	
	BASIC_READ("basic:read"),
	BASIC_WRITE("basic:write"),
	PRODUCT_READ("product:read"),
	PRODUCT_WRITE("product:write");
	
	private final String permission;
	
	private ApplicationRolePermissions(String permission) {
		this.permission = permission;
	}
	
	public String getPermission(){
		return this.permission;
	}

}
