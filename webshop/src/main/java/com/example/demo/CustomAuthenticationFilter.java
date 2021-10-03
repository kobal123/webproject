package com.example.demo;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;


public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private final AuthenticationManager manager;
	
	public CustomAuthenticationFilter(AuthenticationManager manager) {
		this.manager=manager;
	}

	
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		
		return manager.authenticate(token);
	}




	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user = (User)authResult.getPrincipal();
		Algorithm algorithm = Algorithm.HMAC256("123456789".getBytes());
		
		String jwtAccessToken = JWT.create()
							   .withSubject(user.getUsername())
							   .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 *10))
							   .withClaim("roles", user.getAuthorities().stream().
									   map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
							   .sign(algorithm);
		
		String jwtRefreshToken = JWT.create()
				   .withSubject(user.getUsername())
				   .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 *10))
				   .withClaim("roles", user.getAuthorities().stream().
						   map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				   .sign(algorithm);
							   
		
		Map<String,String> tokens = new HashMap<>();
		tokens.put("accessToken", jwtRefreshToken);
		tokens.put("refreshToken", jwtRefreshToken);
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
	}

}
