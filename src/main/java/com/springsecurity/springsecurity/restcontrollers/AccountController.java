package com.springsecurity.springsecurity.restcontrollers;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.springsecurity.customexceptions.ApplicationException;
import com.springsecurity.springsecurity.dto.AccountDTO;
import com.springsecurity.springsecurity.dto.UserDTO;
import com.springsecurity.springsecurity.entity.Account;
import com.springsecurity.springsecurity.enumerations.ResponseStatusCode;
import com.springsecurity.springsecurity.pojo.Success;
import com.springsecurity.springsecurity.security.JWTGenerator;
import com.springsecurity.springsecurity.security.JWTUser;
import com.springsecurity.springsecurity.service.AccountService;
import com.springsecurity.springsecurity.util.EntityDTOConverter;


@RestController
//@PreAuthorize("hasAuthority('ROLE_DOMAIN_USER')")
@RequestMapping(value="/api")
public class AccountController {

private JWTGenerator generator;
	
	public AccountController(JWTGenerator generator) {
		// TODO Auto-generated constructor stub
		this.generator=generator;
	}
	
    @Autowired
    private AccountService accountService;
    
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    
    @GetMapping
    public String unsecured()
    {
    	
    	return "I am unsecure! :(";
    }
    
    @RequestMapping(value = "/user/{id}", method=RequestMethod.DELETE)
	public Success delete(@PathVariable long id) {
	    logger.trace("delete called");
    	Success st = accountService.delete(id);
		
		return st;
	}
    
    @RequestMapping(value = "/user/", method=RequestMethod.POST)
	public Success create(@Valid @RequestBody AccountDTO acDTO) {
	   	
    	Success st = accountService.create(acDTO);
		
    	return st;
	}
    
    @RequestMapping(value = "/user/", method=RequestMethod.PUT)
	public Success updateUser(@RequestBody AccountDTO accDto) {		
		
    	Success st = accountService.updateUser(accDto);
		
    	return st;
	}
    
    
	@RequestMapping(value = "/findByMail/{email}", method=RequestMethod.GET)
	public AccountDTO getByEmail(@PathVariable String email)
	{
		Account account = accountService.getByEmail(email);
		if(account!=null){
			AccountDTO acDTO = EntityDTOConverter.entityToDTO(account);
			return acDTO;
		}
		return new AccountDTO();	
	}
	
	
	@RequestMapping(value = "/findByUsername/{username}", method=RequestMethod.GET)
	public AccountDTO getByUsername(@PathVariable String username){
		
		Account account = accountService.getByUsername(username);		
		if(account!=null){
			AccountDTO acDTO = EntityDTOConverter.entityToDTO(account);
			return acDTO;
		}
		return new AccountDTO();		
	}
	
	
	@RequestMapping(value = "/findByMobile/{mobile}", method=RequestMethod.GET)
	public AccountDTO getByMobile(@PathVariable String mobile){
		
		Account account = accountService.getByMobile(mobile);
		
		if(account!=null){
			AccountDTO acDTO = EntityDTOConverter.entityToDTO(account);
			return acDTO;
		}	
		return new AccountDTO();		
	}
	
	@RequestMapping(value = "/user/login", method=RequestMethod.POST)
	public AccountDTO getUser(@Valid @RequestBody UserDTO acDTO){
		
		Account account = accountService.getUser(acDTO.getUsername(), acDTO.getPassword());
		
		if(account.getUsername()!=null){
			AccountDTO accountDTO = EntityDTOConverter.entityToDTO(account);
			return accountDTO;
		}
		return new AccountDTO();		
	}
}
