package com.springsecurity.springsecurity.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO {

	@Pattern(regexp="[a-zA-z]{5,20}", message = "username can not contain numbers or spaces\\nLimit : 5 to 20")
	@NotEmpty(message = "Please enter your Username.")
	@Size(min = 5, max = 20, message = "Username Limit: 5 to 20 characters")
	private String username;
	
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,25}$", message = "Contains at least one digit\n" + 
			"Contains at least one lower alpha char and one upper alpha char\n" + 
			"Contains at least one char within a set of special chars (@#%$^ etc.)\n" + 
			"Does not contain space, tab, etc."+
			"Limit : 8 to 25")
	@NotEmpty(message = "Please enter your Password.")
	@Size(min = 8, max = 25, message = "Password Limit: 8 to 25 numbers")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
