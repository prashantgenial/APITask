package com.prashant.examples;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class APINotFoundException extends RuntimeException {

	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public APINotFoundException(String exception) {
		    super(exception);
		  }
}

