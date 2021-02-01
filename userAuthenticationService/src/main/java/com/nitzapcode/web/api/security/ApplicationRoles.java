package com.nitzapcode.web.api.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Lists;
import static com.nitzapcode.web.api.security.ApplicationPermissions.*;

public enum ApplicationRoles {
	
	BASIC(Lists.newArrayList(BASIC_READ, BASIC_WRITE)),
	ADMIN(Lists.newArrayList(BASIC_READ, ADMIN_READ, BASIC_WRITE, ADMIN_WRITE )),
	ADMIN_TRAINEE(Lists.newArrayList(BASIC_READ, ADMIN_READ));
	
	private final List<ApplicationPermissions> appPermissions;
	
	private ApplicationRoles(List<ApplicationPermissions> appPermissions) {
		this.appPermissions = appPermissions;
		
	}

	public List<ApplicationPermissions> getAppPermissions() {
		return appPermissions;
	}
	
	public List<SimpleGrantedAuthority> getGrantedAUthorities(){
		
		var grantedAuthorities = this.getAppPermissions().stream()
		.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
		.collect(Collectors.toList());
		
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
		
		return grantedAuthorities;
		
	}
	
	

}
