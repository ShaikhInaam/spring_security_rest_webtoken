package com.springsecurity.springsecurity.service.impl;



import java.util.Optional;

import javax.security.auth.login.AccountException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.springsecurity.springsecurity.dto.AccountDTO;
import com.springsecurity.springsecurity.entity.Account;
import com.springsecurity.springsecurity.enumerations.ResponseStatusCode;
import com.springsecurity.springsecurity.hasing.BCryptConverter;
import com.springsecurity.springsecurity.pojo.Success;
import com.springsecurity.springsecurity.repository.AccountRepository;
import com.springsecurity.springsecurity.service.AccountService;
import com.springsecurity.springsecurity.util.EntityDTOConverter;
import com.springsecurity.springsecurity.util.StringUtilities;
import com.springsecurity.springsecurity.customexceptions.ApplicationException;
import com.springsecurity.springsecurity.enumerations.ResponseStatusCode;;



@Service
public class AccountServiceImpl implements AccountService {
    
    static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    
    @Autowired
    private AccountRepository accountRepository;
   
	@Override
	public Success delete(Long id) {
		
		Optional<Account> op = accountRepository.findById(id);
		
		if(!op.isPresent())
		{
			throw new ApplicationException(ResponseStatusCode.USER_NOT_FOUND);
			
		}
		
		accountRepository.deleteById(id);	
		
		Success suc=new Success(ResponseStatusCode.SUCCESS_MESSAGE.getCode(), ResponseStatusCode.SUCCESS_MESSAGE.getDescription());
		return suc;
	}
	
	@Override
	public Account getByEmail(String email){
		Account account = accountRepository.findByEmail(email);
		return account;		
	}
		
	@Override
	public Account getByUsername(String username){
		
		Account account = accountRepository.findByUsername(username);
		
		return account;		
	}
		
	@Override
	public Account getByMobile( String mobile){
		
		Account account = accountRepository.findByMobile(mobile);
			
		return account;		
	}
		
	@Override
	public Success create(AccountDTO acDTO) {
		logger.info("AccountServiceImpl.java : create() called: info");
		logger.trace("AccountServiceImpl.java : create() called: trace");
		
		AccountDTO accountDTO=new AccountDTO();
		Account ac;
		
		logger.info("AccountServiceImpl.java : checking for username existance : info()");
		logger.trace("AccountServiceImpl.java : checking for username existance: trace()");
		ac = accountRepository.findByUsername(acDTO.getUsername());
		
		if(ac!=null){
			accountDTO = EntityDTOConverter.entityToDTO(ac);
		}
		
		if(!StringUtilities.isEmpty(accountDTO.getFullname())) {
			logger.info("AccountServiceImpl.java : throwing username already exists : info");
			logger.trace("AccountServiceImpl.java : throwing username already exists : trace");
			throw new ApplicationException(ResponseStatusCode.USERNAME_ALREADY_EXIST);
		}
		
		logger.info("AccountServiceImpl.java : checking for cell# existance : info");
		logger.trace("AccountServiceImpl.java : checking for cell# existance : trace");
		
		ac=accountRepository.findByMobile(acDTO.getMobile());
		if(ac!=null){
			accountDTO = EntityDTOConverter.entityToDTO(ac);
		}
		
		if(!StringUtilities.isEmpty(accountDTO.getFullname())){
			logger.info("AccountServiceImpl.java : throwing cell# already exists: info");
			logger.trace("AccountServiceImpl.java : throwing cell# already exists: trace");
			throw new ApplicationException(ResponseStatusCode.CELL_ALREADY_EXIST);
		}
		
		logger.info("AccountServiceImpl.java : checking for email existance : info");
		logger.trace("AccountServiceImpl.java : checking for email existance : trace");
		ac=accountRepository.findByEmail(acDTO.getEmail());
		if(ac!=null){
			accountDTO = EntityDTOConverter.entityToDTO(ac);
		}
		
		if(!StringUtilities.isEmpty(accountDTO.getFullname())){
			logger.info("AccountServiceImpl.java : throwing email already exists : info");
			logger.trace("AccountServiceImpl.java : throwing email already exists : trace");
			throw new ApplicationException(ResponseStatusCode.EMAIL_ALREADY_EXIST);
		}
		
		acDTO.setPassword(BCryptConverter.convert(acDTO.getPassword()));
		ac = EntityDTOConverter.dtoToEntity(acDTO);
		accountRepository.save(ac);
		
		logger.info("AccountServiceImpl.java : create(): user created successfully: info");
		logger.trace("AccountServiceImpl.java : create(): user created successfully: trace");
		
		Success suc=new Success(ResponseStatusCode.SUCCESS_MESSAGE.getCode(), ResponseStatusCode.SUCCESS_MESSAGE.getDescription());
		return suc;
	}
		
	@Override
	public Success updateUser(AccountDTO ac) {
		
		Account account = accountRepository.findById(ac.getId());
		
		if(account==null)
		{
			throw new ApplicationException(ResponseStatusCode.USER_NOT_FOUND);
		}
		
		account.setUsername(ac.getUsername());
		account.setEmail(ac.getEmail());
		account.setFullname(ac.getFullname());
		account.setMobile(ac.getMobile());
		account.setPassword(ac.getPassword());
		
		accountRepository.save(account);
			
		Success suc=new Success(ResponseStatusCode.SUCCESS_MESSAGE.getCode(), ResponseStatusCode.SUCCESS_MESSAGE.getDescription());
		return suc;
			
		}

	@Override
	public Account getUser(String username, String password) {
		
		logger.info("AccountServiceImpl.java : getUser() is executing :info");
		logger.trace("AccountServiceImpl.java : getUser() is executing : trace");
		
		Account account = accountRepository.findByUsername(username);
		if(account!=null) {
			if(BCryptConverter.compare(password, account.getPassword()))
			{
				logger.info("AccountServiceImpl.java : user login success: info");
				logger.trace("AccountServiceImpl.java : user login success: trace");
				return account;
			}
			else
				throw new ApplicationException(ResponseStatusCode.PASSWORD_NOT_FOUND);
		}
		else
			throw new ApplicationException(ResponseStatusCode.USER_NOT_FOUND);
			
	}
}