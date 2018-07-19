package com.springsecurity.springsecurity.pojo;

public class Success {

	private int code;
    private String message;

    public Success() {
    }

    public Success(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
	
}
