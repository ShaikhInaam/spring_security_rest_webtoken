package com.springsecurity.springsecurity.security;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTGenerator {
	
	public String generate (JWTUser user)
	{		
		Claims claims = Jwts.claims().setSubject(user.getUsername());
		claims.put("userID", String.valueOf(user.getId()));
		claims.put("role", user.getRole());

		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, "youtube").compact();
	}

}
