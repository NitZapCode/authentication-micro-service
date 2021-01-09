package com.curatorsodisha.web.security;

import static com.curatorsodisha.web.security.ApplicationRolePermissions.BASIC_READ;
import static com.curatorsodisha.web.security.ApplicationRolePermissions.BASIC_WRITE;
import static com.curatorsodisha.web.security.ApplicationRolePermissions.PRODUCT_READ;
import static com.curatorsodisha.web.security.ApplicationRolePermissions.PRODUCT_WRITE;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.google.common.collect.Sets;

public enum ApplicationRoles {
	
	
	BASIC(Sets.newHashSet(BASIC_READ,BASIC_WRITE)),
	ADMIN(Sets.newHashSet(BASIC_READ,BASIC_WRITE,PRODUCT_READ,PRODUCT_WRITE)),
	ADMIN_TRAINEE(Sets.newHashSet(BASIC_READ, PRODUCT_READ));
	
	private final Set<ApplicationRolePermissions> applicationPermissions;
	
	private ApplicationRoles(Set<ApplicationRolePermissions> applicationPermissions) {
		this.applicationPermissions = applicationPermissions;
	}
	
	public Set<ApplicationRolePermissions> getApplicationPermissions(){
		return this.applicationPermissions;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		var grantedAuthorities =  this.getApplicationPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
		.collect(Collectors.toSet());
		
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
		return grantedAuthorities;
	}

}
