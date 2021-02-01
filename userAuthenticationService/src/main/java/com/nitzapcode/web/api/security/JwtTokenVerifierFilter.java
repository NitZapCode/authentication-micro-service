package com.nitzapcode.web.api.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTokenVerifierFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorization = request.getHeader("Authorization");
		if(authorization == null || !authorization.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String token  = authorization.replace("Bearer ", "");
		String key = "hg356726bfg36rgr3fdfsd2vxghg356726bfg36rgr3fdfsd2vxghg356726bfg36rgr3fdfsd2vxghg356726bfg36rgr3fdfsd2vxghg356726bfg36rgr3fdfsd2vxghg356726bfg36rgr3fdfsd2vxg";
		JwtParser parser =  Jwts.parserBuilder()
		     .setSigningKey(Keys.hmacShaKeyFor(key.getBytes())).build();
		
		Claims body = (Claims) parser.parse(token).getBody();
		String userName = body.getSubject();
		@SuppressWarnings("unchecked")
		var authorities = (List<Map<String, String>>)body.get("authority");
		List<SimpleGrantedAuthority> grantedAuth =  authorities.stream().map(e -> new SimpleGrantedAuthority(e.get("authority"))).collect(Collectors.toList());
		Authentication authentication = new UsernamePasswordAuthenticationToken(userName, null, grantedAuth);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
		
	}

}
