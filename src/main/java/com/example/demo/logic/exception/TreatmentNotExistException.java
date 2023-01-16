package com.example.demo.logic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TreatmentNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2840992983195997661L;

	public TreatmentNotExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TreatmentNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public TreatmentNotExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TreatmentNotExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public TreatmentNotExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
