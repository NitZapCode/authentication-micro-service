package com.nitzapcode.web.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nitzapcode.web.api.auth.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
	
	private UserService userAuthService;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public ApplicationSecurity(UserService userAuthService, PasswordEncoder passwordEncoder) {
		this.userAuthService = userAuthService;
		this.passwordEncoder  = passwordEncoder;
	}
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilter(new JwtUsernamePasswordAuthenticationFilter(authenticationManager()))
		.addFilterAfter(new JwtTokenVerifierFilter(), JwtUsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		.antMatchers("/api/auth/user")
		.permitAll()
		.anyRequest()
		.authenticated();
		
		
		http.headers().frameOptions().disable();
		
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userAuthService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
//		auth.userDetailsService(userAuthService).passwordEncoder(passwordEncoder);
	}
	
	

}
