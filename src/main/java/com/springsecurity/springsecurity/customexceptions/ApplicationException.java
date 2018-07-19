package com.springsecurity.springsecurity.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.springsecurity.springsecurity.enumerations.ResponseStatusCode;


@ResponseStatus(value = HttpStatus.ACCEPTED)
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 2804824959637443055L;
	
	private ResponseStatusCode responseStatusCode;

	public ApplicationException(String message) {
        super(message);
    }
	
	public ApplicationException(ResponseStatusCode code, String message) {
        super(message);
        this.responseStatusCode = code;
    }
	
	public ApplicationException(ResponseStatusCode code) {
        super(code.getDescription());
        this.responseStatusCode = code;
    }
	
	public ResponseStatusCode getResponseStatusCode() {
		return responseStatusCode;
	}
	
}
