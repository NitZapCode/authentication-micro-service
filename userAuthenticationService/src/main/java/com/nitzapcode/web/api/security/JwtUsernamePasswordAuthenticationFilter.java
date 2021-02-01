package com.nitzapcode.web.api.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nitzapcode.web.api.dto.UserLoginRequestDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	AuthenticationManager authenticationManager;
	
	public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
			this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			UserLoginRequestDTO userLoginDto = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequestDTO.class);
			
			return authenticationManager.authenticate(
					  new UsernamePasswordAuthenticationToken (
							  userLoginDto.getUserName(), 
							  userLoginDto.getPassword()
							  ));
			
		} catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain chain,
			Authentication authResult) 
					throws IOException, ServletException {
		
		String userName = ((User)authResult.getPrincipal()).getUsername();
		String key = "hg356726bfg36rgr3fdfsd2vxghg356726bfg36rgr3fdfsd2vxghg356726bfg36rgr3fdfsd2vxghg356726bfg36rgr3fdfsd2vxghg356726bfg36rgr3fdfsd2vxghg356726bfg36rgr3fdfsd2vxg";
		String token = Jwts.builder()
		.setSubject(userName)
		.claim("authority",authResult.getAuthorities())
		.setExpiration(new Date(System.currentTimeMillis()+Long.parseLong("864000000")))
		.signWith(Keys.hmacShaKeyFor(key.getBytes()))
		.compact();
		
	   response.addHeader("Authorization", "Bearer "+token);
		
	}

	
	
}
