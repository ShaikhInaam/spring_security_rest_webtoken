package com.springsecurity.springsecurity.service;

import com.springsecurity.springsecurity.dto.AccountDTO;
import com.springsecurity.springsecurity.entity.Account;
import com.springsecurity.springsecurity.pojo.Success;

public interface AccountService {
   
    public Success delete(Long id);
    public Success create(AccountDTO acDto);
	public Account getByEmail(String email);
	public Account getByUsername(String username);
	public Account getByMobile( String mobile);
	public Success updateUser(AccountDTO ac);
	public Account getUser(String username, String password);
}
