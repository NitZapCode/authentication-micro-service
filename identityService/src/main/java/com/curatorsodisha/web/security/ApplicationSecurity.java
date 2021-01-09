package com.curatorsodisha.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import static com.curatorsodisha.web.security.ApplicationRoles.*;
import static com.curatorsodisha.web.security.ApplicationRolePermissions.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter{
	
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public ApplicationSecurity(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		  .csrf().disable()
		  .authorizeRequests()
//		  .antMatchers(HttpMethod.POST,"/management/identity").hasAuthority(PRODUCT_WRITE.getPermission())
//		  .antMatchers(HttpMethod.DELETE,"/management/identity").hasAuthority(PRODUCT_WRITE.getPermission())
//		  .antMatchers(HttpMethod.PUT,"/management/identity").hasAuthority(PRODUCT_WRITE.getPermission())
//		  .antMatchers(HttpMethod.GET,"/management/identity").hasAnyRole(ADMIN_TRAINEE.name(), ADMIN.name())
		  .anyRequest()
		  .authenticated()
		  .and()
		  .httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails jayitaDetails =  User
		 .withUsername("Jayita")
		 .password(passwordEncoder.encode("codeword"))
//		 .roles(ADMIN.name())
		 .authorities(ADMIN.getGrantedAuthorities())
		 .build();
		
		UserDetails pramodeDetails = User
				.withUsername("Prashant")
				.password(passwordEncoder.encode("abc123"))
//				.roles(BASIC.name())
				.authorities(BASIC.getGrantedAuthorities())
				.build();
		
		UserDetails arunDetails = User
				.withUsername("Arun")
				.password(passwordEncoder.encode("codeword2"))
				.authorities(ADMIN_TRAINEE.getGrantedAuthorities())
//				.roles(ADMIN_TRAINEE.name())
				.build();
		
		
		return new InMemoryUserDetailsManager(jayitaDetails, pramodeDetails, arunDetails);
	}
	
	

}
