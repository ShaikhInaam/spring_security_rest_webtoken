package com.springsecurity.springsecurity.enumerations;

public enum ResponseStatusCode {
	
	USERNAME_ALREADY_EXIST(101, "Username already exist"),
	EMAIL_ALREADY_EXIST(102, "Email already exist"),
	CELL_ALREADY_EXIST(103,"Cell# already exist"),
	
	SUCCESS_MESSAGE(0,"Success!"),
	
	INCORRECT_TOKEN(121,"Incorrect Token"),
	MISSIING_TOKEN(122,"Token is missing"),
	
	USER_NOT_FOUND(109,"no such user found"),
	PASSWORD_NOT_FOUND(110,"user found! but password is incorrect");
	
	private Integer code;
	private String description;
	
	private ResponseStatusCode(Integer code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
}
