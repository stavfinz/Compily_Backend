package com.example.demo.logic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PatientNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5047097965388705949L;
	
	public PatientNotExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PatientNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PatientNotExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PatientNotExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PatientNotExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	

}
