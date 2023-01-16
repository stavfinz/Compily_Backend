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
	}

	public PatientNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PatientNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public PatientNotExistException(String message) {
		super(message);
	}

	public PatientNotExistException(Throwable cause) {
		super(cause);
	}

}
