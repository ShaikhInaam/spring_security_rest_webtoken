package com.springsecurity.springsecurity.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

	
	private JWTGenerator generator;
	
	public TokenController(JWTGenerator generator) {
		// TODO Auto-generated constructor stub
		this.generator=generator;
	}
	
	
	@PostMapping
	public String generate(@RequestBody JWTUser user)
	{
		
		return generator.generate(user);
		
		
	}
	
	
}
