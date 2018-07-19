package com.springsecurity.springsecurity.security;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JWTValidator {
	
	private String secret = "youtube";

	public JWTUser validate(String token) {
	
		JWTUser jWTUser=null;
		
	try {
			
		
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		
		jWTUser =new JWTUser();
		jWTUser.setUsername(body.getSubject());
		jWTUser.setRole((String)body.get("role"));
		jWTUser.setId(Long.parseLong((String)body.get("userId")));
		
	}catch(Exception ex) {
		
	}	
	
		return jWTUser;
	}

}
