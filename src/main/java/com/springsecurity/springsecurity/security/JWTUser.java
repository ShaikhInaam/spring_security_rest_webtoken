package com.springsecurity.springsecurity.security;

public class JWTUser {
	
	private String username,role,password;
	private long id;

	public void setUsername(String username) {
		this.username=username;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public String getRole() {
		return role;
	}

	public long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
