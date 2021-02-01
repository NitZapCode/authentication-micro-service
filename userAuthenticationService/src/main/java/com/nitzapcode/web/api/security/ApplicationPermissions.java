package com.nitzapcode.web.api.security;

public enum ApplicationPermissions {
	
	BASIC_READ("basic:read"),
	BASIC_WRITE("basic:write"),
	ADMIN_READ("admin:read"),
	ADMIN_WRITE("admin:write");
	
	private final String permission;
	
	ApplicationPermissions(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}

	

}
