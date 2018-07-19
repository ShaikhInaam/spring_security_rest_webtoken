package com.springsecurity.springsecurity.util;

import org.springframework.beans.BeanUtils;

import com.springsecurity.springsecurity.dto.AccountDTO;
import com.springsecurity.springsecurity.entity.Account;


public class EntityDTOConverter {

	public static AccountDTO entityToDTO(Account ac){
		AccountDTO acDto=new AccountDTO();
		BeanUtils.copyProperties(ac, acDto);
		
		return acDto;
	}
	
	public static Account dtoToEntity(AccountDTO acDto){
		Account ac=new Account();
		BeanUtils.copyProperties(acDto, ac);		
		
		return ac;
	}
}