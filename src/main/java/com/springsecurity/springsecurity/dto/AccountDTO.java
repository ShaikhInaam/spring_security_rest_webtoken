package com.springsecurity.springsecurity.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class AccountDTO {
	
	AccountDTO accountDTO;
	
	@Pattern(regexp="[a-zA-z- ]{10,50}", message = "Full name can not contain numbers. Limit : 10 to 50")
	@NotEmpty(message = "Please enter your Full Name.")
	@Size(min = 10, max = 50, message = "Full Name Limit: 10 to 50 characters")
	private String fullname;
	
	@Pattern(regexp="[a-zA-z]{5,20}", message = "username can not contain numbers or spaces. Limit : 5 to 20")
	@NotEmpty(message = "Please enter your Username.")
	@Size(min = 5, max = 20, message = "Username Limit: 5 to 20 characters")
	private String username;
	
	
	@NotEmpty(message = "Please enter your Email.")
	@Email(message = "please provide a valid Email")
	private String email;
	
	@Pattern(regexp="[0-9]{11,15}", message = "Cell# should be a number. Limit : 11 to 15")
	@NotEmpty(message = "Please enter your Cell#.")
	@Size(min = 11, max = 15, message = "Cell# Limit: 11 to 15 numbers")
	private String mobile;
	
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,25}$", message = "Contains at least one digit :" + 
			"Contains at least one lower alpha char and one upper alpha char: " + 
			"Contains at least one char within a set of special chars (@#%$^ etc.): " + 
			"Does not contain space, tab, etc.: "+
			"Limit : 8 to 25")
	@NotEmpty(message = "Please enter your Password.")
	@Size(min = 8, max = 25, message = "Password Limit: 8 to 25 numbers")
	private String password;
	
	private long id;
	
	
	public long getId(){
		return id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setId(long id){
		this.id = id;
	}
}
