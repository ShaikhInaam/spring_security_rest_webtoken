package com.springsecurity.springsecurity.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springsecurity.springsecurity.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long>{

	  @Query("from Account a where a.email=?1")
	  public Account findByEmail(String email);
	  
	  public Account findByUsername(String username);
	  public Account findByMobile(String mobile);
	  public Account findById(long id);
	  
	  @Query("from Account a where a.username=?1 and a.password=?2")
	  public Account findUserByAccount(String username,String password);
	
	
}
