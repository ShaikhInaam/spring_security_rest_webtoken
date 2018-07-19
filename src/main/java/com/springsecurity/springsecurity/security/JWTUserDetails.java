package com.springsecurity.springsecurity.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JWTUserDetails implements UserDetails {

	private String username,token;
	private long id;
	private Collection<? extends GrantedAuthority> authorities;
	
	public JWTUserDetails(String username, long id, String token, List<GrantedAuthority> authorities) {
		// TODO Auto-generated constructor stub
		
		this.username=username;
		this.id=id;
		this.token=token;
		this.authorities=authorities;
	}
	
	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


	public String getToken() {
		return token;
	}
	
	public long getId() {
		return id;
	}

	public Collection<? extends GrantedAuthority> getList() {
		return authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
}