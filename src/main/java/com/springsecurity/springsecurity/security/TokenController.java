package com.springsecurity.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.springsecurity.customexceptions.ApplicationException;
import com.springsecurity.springsecurity.dto.AccountDTO;
import com.springsecurity.springsecurity.dto.UserDTO;
import com.springsecurity.springsecurity.entity.Account;
import com.springsecurity.springsecurity.enumerations.ResponseStatusCode;
import com.springsecurity.springsecurity.service.AccountService;

@RestController
@RequestMapping("/token")
public class TokenController {

	@Autowired
    private AccountService accountService;
	
	private JWTGenerator generator;
	
	public TokenController(JWTGenerator generator) {
	
		this.generator=generator;
	}
	
	
	@RequestMapping(value = "/", method=RequestMethod.POST, produces="application/json")
    public ResponseEntity<String> generate(@RequestBody JWTUser user)
	{
		
		Account account = accountService.getUser(user.getUsername(), user.getPassword());
		
		if(account.getId()!=user.getId())
			throw new ApplicationException(ResponseStatusCode.USER_NOT_FOUND);
    	
		String token = generator.generate(user);
		return new ResponseEntity<String>(token, HttpStatus.OK);	
	}
}
