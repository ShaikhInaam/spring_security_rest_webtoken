package com.springsecurity.springsecurity.security;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springsecurity.springsecurity.customexceptions.ApplicationException;
import com.springsecurity.springsecurity.enumerations.ResponseStatusCode;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter{
	
	public JWTAuthenticationTokenFilter() {
		super("/user/**");
		// TODO Auto-generated constructor stub
	}

	Logger logger = LoggerFactory.getLogger(JWTAuthenticationTokenFilter.class);

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)throws AuthenticationException, IOException, ServletException {
		
		
		String header = request.getHeader("Authorization");
		logger.info("-----------HEADER = "+header);
		
		logger.info("-----------CHECKING TOKEN HEADER");
		if(header == null || !header.startsWith("Inaam "))
			throw new ApplicationException(ResponseStatusCode.MISSIING_TOKEN);
		
		String authenticationToken = header.substring(6);
		JWTAuthenticationToken token = new JWTAuthenticationToken(authenticationToken);
		
		
		return getAuthenticationManager().authenticate(token);
	}



	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		logger.info("-----------SUCCESSFUL AUTHENTICATION IS WOKING!");
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}
	
}
