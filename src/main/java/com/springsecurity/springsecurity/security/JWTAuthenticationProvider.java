package com.springsecurity.springsecurity.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.springsecurity.springsecurity.customexceptions.ApplicationException;
import com.springsecurity.springsecurity.enumerations.ResponseStatusCode;

@Component
public class JWTAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

	@Autowired
	private JWTValidator jWTValidator;

	@Override
	public boolean supports(Class<?> aClass) {
		
		return (JWTAuthenticationToken.class.isAssignableFrom(aClass));
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)throws AuthenticationException {
		
		JWTAuthenticationToken jWTAuthenticationToken = (JWTAuthenticationToken)authentication;
		String token=jWTAuthenticationToken.getToken();
		JWTUser jWTUser = jWTValidator.validate(token);
		
		if(jWTUser==null)
			throw new ApplicationException(ResponseStatusCode.INCORRECT_TOKEN);
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(jWTUser.getRole());
		
		return new JWTUserDetails(jWTUser.getUsername(), jWTUser.getId(), token , grantedAuthorities);
	}
}